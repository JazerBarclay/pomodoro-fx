package dev.jazer.pomodoroFX.widgets.button;

import javafx.scene.paint.Color;

public class CloseButton extends CanvasButton {

	public CloseButton(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public CloseButton(int x, int y, int size) {
		this(x, y, size, size);
	}

	public CloseButton(int x, int y) {
		this(x, y, 25, 25);
	}

	public CloseButton() {
		this(0, 0);
	}
	
	@Override
	public void drawClicked() {
		gc.setFill(Color.GREY);
		gc.fillPolygon(new double[]{5, width - 3, width - 5, 3}, new double[]{3, height - 5, height - 3, 5}, 4);
		gc.fillPolygon(new double[]{width - 5, width - 3, 5, 3}, new double[]{3, 5, height - 3, height - 5}, 4);
	}

	@Override
	public void drawReleased() {
		drawClicked();
	}

}
