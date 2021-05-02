package dev.jazer.pomodoroFX.widgets.button;

import javafx.scene.paint.Color;

public class CornerButton extends CanvasButton {

	public CornerButton(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public CornerButton(int x, int y, int size) {
		this(x, y, size, size);
	}

	public CornerButton(int x, int y) {
		this(x, y, 25, 25);
	}

	public CornerButton() {
		this(0, 0);
	}
	
	@Override
	public void drawClicked() {
		gc.setFill(Color.color(.4, .4, .4));
		
		gc.fillPolygon(new double[]{width, width, 0}, new double[]{0, height, height}, 3);
//		gc.strokePolygon(new double[]{width, width, 0}, new double[]{0, height, height}, 3);
	}

	@Override
	public void drawReleased() {
		drawClicked();
	}
	
}
