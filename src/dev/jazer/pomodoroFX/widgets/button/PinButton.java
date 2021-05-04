package dev.jazer.pomodoroFX.widgets.button;

import javafx.scene.paint.Color;

public class PinButton extends CanvasSwitchButton {

	public PinButton(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public PinButton(int x, int y, int size) {
		this(x, y, size, size);
	}

	public PinButton(int x, int y) {
		this(x, y, 25, 25);
	}

	public PinButton() {
		this(0, 0);
	}

	@Override
	public void drawClicked() {
		gc.setStroke(Color.color(0.168, 0.188, 0.231));
		gc.strokePolygon(
				new double[] {6, 14, 14, 11, 11, 13, 13, 11, 10,  9,  7,  7,  9, 9, 6}, 
				new double[] {3,  3,  4,  4, 9, 9, 10, 10, 15, 10, 10, 9, 9, 4, 4}, 
				15);
	}

	@Override
	public void drawReleased() {
		gc.setFill(Color.color(0.168, 0.188, 0.231));
		gc.fillPolygon(
				new double[] {5, 15, 15, 12, 12, 14, 14, 11, 10,  9,  6,  6,  8, 8, 5}, 
				new double[] {2,  2,  4,  4, 10, 10, 12, 12, 18, 12, 12, 10, 10,  4, 4}, 
				15);
	}
	
	
}
