package dev.jazer.pomodoroFX;

import dev.jazer.pomodoroFX.widgets.button.*;
import dev.jazer.pomodoroFX.widgets.screen.Screen;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View {
	
	private boolean running;
	
	private Model model;
	
	private Stage window;
	private Screen screen;

	private Label lblTimer;
	private CloseButton btnClose;
	private PinButton btnPin;
	private PlayButton btnPlay;
	private StopButton btnStop;
	private CornerButton btnPosition;

	public View(Stage window, Model model) {
		this.window = window;
		this.model = model;
		
		window.setWidth(200);
		window.setHeight(100);
		
		lblTimer = new Label("25:00");
		lblTimer.setFont(new Font("Arial", 32));
		lblTimer.setTextFill(Color.WHITE);
		lblTimer.setLayoutY(13);
		lblTimer.setPrefWidth(window.getWidth());
		lblTimer.setAlignment(Pos.CENTER);

		btnClose = new CloseButton((int)window.getWidth()-25, 5, 20);
		btnPin = new PinButton(5,5,20);
		btnPlay = new PlayButton((int)(window.getWidth()/2)-12, 60, 25);
		btnStop = new StopButton((int)(window.getWidth()/2)-12, 60, 25);
		btnPosition = new CornerButton((int)(window.getWidth()-20), 80, 15);
		
		screen = new Screen(window, new Pane());
		screen.add(lblTimer);
		screen.add(btnClose);
		screen.add(btnPin);
		screen.add(btnPosition);

		screen.setDraggableElement(lblTimer);
		screen.positionBottomRight();
		

		screen.add(btnPlay);
		setPlay();
		
		window.setScene(screen);
	}
	
	public void setPlay() {
		if (!running) return;
		running = false;
		screen.add(btnPlay);
		screen.remove(btnStop);
	}
	
	public void setStop() {
		if (running) return;
		running = true;
		screen.add(btnStop);
		screen.remove(btnPlay);
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
		btnClose.setOnClickHandler(() -> { window.hide(); controller.onClosePressed(); });
		btnPlay.setOnClickHandler(() -> controller.onPlayPressed());
		btnStop.setOnClickHandler(() -> controller.onStopPressed());
		btnPosition.setOnClickHandler(() -> screen.positionBottomRight());
		
		btnPin.setOnClickHandler(() -> {
			screen.setDraggableElement(null);
		});
		btnPin.setOnReleaseHandler(() -> {
			screen.setDraggableElement(lblTimer);
		});
	}
	
	public void update() {
		Platform.runLater(() -> updateTimer((int)(model.durationRemaining/1000/60), (int)(model.durationRemaining/1000)%60));
		Platform.runLater(() -> {
			if (model.timerState == ClockState.STOPPED) setPlay();
			else if (model.timerState == ClockState.WORKING) setStop();
		});
	}

}
