package dev.jazer.pomodoroFX;

public class Controller {

	private Model model;
	
	public Controller(Model model) {
		this.model = model;
	}
	
	public void onClosePressed() {
		System.out.println("Closing...");
		System.exit(0);
	}
	
	public void onPlayPressed() {
		System.out.println("Play");
		model.start();
	}
	
	public void onPlayReleased() {
		System.out.println("Pause");
		model.pause();
	}
	
	public void onStopPressed() {
		System.out.println("Stop");
		model.stop();
	}
	
}
