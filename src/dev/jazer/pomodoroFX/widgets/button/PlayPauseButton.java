package dev.jazer.pomodoroFX.widgets.button;

import javafx.scene.paint.Color;

public class PlayPauseButton extends CanvasButton {

	public PlayPauseButton(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public PlayPauseButton(int x, int y, int size) {
		this(x, y, size, size);
	}

	public PlayPauseButton(int x, int y) {
		this(x, y, 25, 25);
	}

	public PlayPauseButton() {
		this(0, 0);
	}
	
	@Override
	public void drawClicked() {
		gc.setFill(Color.LIGHTYELLOW);
		gc.fillRect(2, 0, 8, height);
		gc.fillRect(15, 0, 8, height);
	}

	@Override
	public void drawReleased() {
		gc.setFill(Color.LIGHTGREEN);
		gc.fillPolygon(new double[]{2, (width/5)*4+3, 2}, new double[]{0, height/2, height}, 3);
	}

}
