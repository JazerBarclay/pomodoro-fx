package dev.jazer.pomodoroFX;

public class Controller {

	private Model model;
	
	public Controller(Model model) {
		this.model = model;
	}
	
	public void onClosePressed() {
		System.exit(0);
	}
	
	public void onPlayPressed() {
		model.start();
	}
	
	public void onStopPressed() {
		model.stop();
	}
	
}
