package dev.jazer.pomodoroFX;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	
	@Override
	public void start(Stage window) throws Exception {
		
		Model model = new Model();
		View view = new View(window, model);
		Controller controller = new Controller(model);
		
		model.setObserver(() -> view.update());
		view.setController(controller);

		window.setAlwaysOnTop(true);
		window.initStyle(StageStyle.UNDECORATED);
		window.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
