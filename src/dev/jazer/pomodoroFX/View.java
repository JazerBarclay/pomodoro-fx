package dev.jazer.pomodoroFX;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import dev.jazer.pomodoroFX.widgets.CanvasProgressBar;
import dev.jazer.pomodoroFX.widgets.button.*;
import dev.jazer.pomodoroFX.widgets.screen.Screen;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View {
	
	private boolean running;
	private boolean resting;
	
	private Model model;
	
	private Stage window;
	private Screen screen;

	private Canvas bar;
	private Label lblTimer;
	private CloseButton btnClose;
	private PinButton btnPin;
	private PlayButton btnPlay;
	private StopButton btnStop;
	private CanvasProgressBar progress;
	private CornerButton btnPosition;
	
	public View(Stage window, Model model) {
		this.window = window;
		this.model = model;
		
		window.setTitle("Pomodoro Timer by Jazer");
		window.setWidth(220);
		window.setHeight(130);
		
		bar = new Canvas(window.getWidth(), window.getHeight()/2+10);
		bar.getGraphicsContext2D().setFill(Color.WHITE);
		bar.getGraphicsContext2D().fillRect(0, 0, bar.getWidth(), bar.getHeight());
		
		lblTimer = new Label("25:00");
		lblTimer.setLayoutY((int)window.getHeight()/2-38);
		lblTimer.setFont(new Font("Arial", 48));
		lblTimer.setTextFill(Color.color(0.168, 0.188, 0.231));
		lblTimer.setPrefWidth(window.getWidth());
		lblTimer.setAlignment(Pos.CENTER);

		btnClose = new CloseButton((int)window.getWidth()-25, 5, 20);
		btnPin = new PinButton(5,5,20);
		
		btnPlay = new PlayButton((int)(window.getWidth()/2)-10, (int)window.getHeight()-35, 20);
		btnStop = new StopButton((int)(window.getWidth()/2)-10, (int)window.getHeight()-35, 20);
		
		progress = new CanvasProgressBar((int)window.getWidth()/2-50, 5, 100, 20);
		btnPosition = new CornerButton((int)window.getWidth()-20, (int)window.getHeight()-20, 15);
		
		screen = new Screen(window, new Pane());
		screen.setBackground(Color.color(.1,.1,.1));
		screen.add(lblTimer);
		screen.add(btnClose);
		screen.add(btnPin);
		screen.add(progress);
		screen.add(btnPosition);

		screen.setDraggableElement(lblTimer);
		screen.positionBottomRight();
		

		screen.add(btnPlay);
		setStopped();
		
		window.setScene(screen);
	}
	
	public void setStopped() {
		screen.setBackground(Color.color(.8, .4, .4));
		if (!running) return;
		running = false;
		resting = false;
		playWAV("stop");
		screen.add(btnPlay);
		screen.remove(btnStop);
		progress.reset();
	}

	public void setRunning() {
		screen.setBackground(Color.color(.369, .557, .745));
		if (resting) {
			resting = false;
			progress.increment();
			playWAV("work");
		}
		if (running) return;
		running = true;
		playWAV("work");
		screen.add(btnStop);
		screen.remove(btnPlay);
		progress.increment();
	}

	public void setResting() {
		screen.setBackground(Color.LIGHTGREEN);
		if (resting) return;
		resting = true;
		playWAV("break");
		progress.increment();
	}
	
	public void updateTimer(int mins, int secs) {
		String s = "";
		if (mins < 10) s+="0";
		s+=mins;
		s+=":";
		if (secs < 10) s+="0";
		s+=secs;
		lblTimer.setText(s);
	}

	public void setController(Controller controller) {
		btnClose.setOnClickHandler(() -> { 
			window.hide();
			controller.onClosePressed(); 
		});
		
		btnPlay.setOnClickHandler(() -> {
			controller.onPlayPressed();
		});
		
		btnStop.setOnClickHandler(() -> {
			controller.onStopPressed();
		});
		
		btnPosition.setOnClickHandler(() -> screen.positionBottomRight());
		
		btnPin.setOnClickHandler(() -> screen.setDraggableElement(null));
		
		btnPin.setOnReleaseHandler(() -> screen.setDraggableElement(lblTimer));
	}
	
	public static synchronized void playWAV(String fileName) {

		try {
			InputStream is = View.class.getResourceAsStream("/sound/"+fileName+".wav");
			InputStream bufferedIn = new BufferedInputStream(is);
			AudioInputStream st = AudioSystem.getAudioInputStream(bufferedIn);
			Clip clip = AudioSystem.getClip();
			clip.open(st);
			clip.setFramePosition(0);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
		    throw new IllegalArgumentException("Unsupported audio format: '" + fileName + "'", e);
		} catch (LineUnavailableException e) {
		    throw new IllegalArgumentException("Line unavailable. Could not play '" + fileName + "'", e);
		} catch (IOException e) {
		    throw new IllegalArgumentException("IO exception. Could not play '" + fileName + "'", e);
		} catch (Exception e) {
			System.err.println("Failed to play media");
			e.printStackTrace();
		}

	}
	
	public void update() {
		Platform.runLater(() -> updateTimer((int)(model.durationRemaining/1000/60), (int)(model.durationRemaining/1000)%60));
		Platform.runLater(() -> {
			if (model.timerState == ClockState.STOPPED) setStopped();
			else if (model.timerState == ClockState.WORKING) setRunning();
			else if (model.timerState == ClockState.RESTING) setResting();
		});
	}

}
