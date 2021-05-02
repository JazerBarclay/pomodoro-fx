package dev.jazer.pomodoroFX;

import dev.jazer.pomodoroFX.widgets.button.CloseButton;
import dev.jazer.pomodoroFX.widgets.button.CornerButton;
import dev.jazer.pomodoroFX.widgets.button.FastForwardButton;
import dev.jazer.pomodoroFX.widgets.button.PlayPauseButton;
import dev.jazer.pomodoroFX.widgets.button.StopButton;
import dev.jazer.pomodoroFX.widgets.screen.Screen;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View {
	
	private Model model;
	
	private Stage window;
	private Screen screen;

	private Label lblTimer;
	private CloseButton btnClose;
	private PlayPauseButton btnPlay;
	private StopButton btnStop;
	private FastForwardButton btnFF;
	private CornerButton btnPosition;

	public View(Stage window, Model model) {
		this.window = window;
		this.model = model;
		
		window.setWidth(300);
		window.setHeight(100);
		
		lblTimer = new Label("25:00");
		lblTimer.setFont(new Font("Arial", 28));
		lblTimer.setTextFill(Color.WHITE);
		lblTimer.setLayoutY(13);
		lblTimer.setPrefWidth(300);
		lblTimer.setAlignment(Pos.CENTER);

		btnClose = new CloseButton(275, 5, 20);
		btnPlay = new PlayPauseButton(140, 60, 25);
		btnFF = new FastForwardButton(190, 60, 25);
		btnStop = new StopButton(90, 60, 25);
		btnPosition = new CornerButton(280, 80, 15);
		
		screen = new Screen(window, new Pane());
		screen.add(lblTimer);
		screen.add(btnClose);
		screen.add(btnPlay);
		screen.add(btnFF);
		screen.add(btnStop);
		screen.add(btnPosition);

		screen.setDraggableElement(lblTimer);
//		screen.positionBottomRight();
		
		window.setScene(screen);
		
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
		btnPlay.setOnReleaseHandler(() -> controller.onPlayReleased());
		btnStop.setOnClickHandler(() -> controller.onStopPressed());
		btnStop.setOnReleaseHandler(() -> controller.onStopPressed());
		btnPosition.setOnClickHandler(() -> screen.positionBottomRight());
		btnPosition.setOnReleaseHandler(() -> screen.positionBottomRight());
	}
	
	public void update() {
		Platform.runLater(() -> updateTimer((int)(model.durationRemaining/1000/60), (int)(model.durationRemaining/1000)%60));
		btnPlay.setIsClicked((model.timerState == ClockState.STOPPED ? false : true));
	}

}
