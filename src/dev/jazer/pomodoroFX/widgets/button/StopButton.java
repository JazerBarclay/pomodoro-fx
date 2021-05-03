package dev.jazer.pomodoroFX.widgets.button;

import javafx.scene.paint.Color;

public class StopButton extends CanvasButton {
	
	public StopButton(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public StopButton(int x, int y, int size) {
		this(x, y, size, size);
	}

	public StopButton(int x, int y) {
		this(x, y, 25, 25);
	}

	public StopButton() {
		this(0, 0);
	}
	
	@Override
	public void drawClicked() {
		gc.setFill(Color.PALEVIOLETRED);
		gc.fillRect(0, 0, width, height);
	}

	@Override
	public void drawReleased() {
		drawClicked();
	}
	
}