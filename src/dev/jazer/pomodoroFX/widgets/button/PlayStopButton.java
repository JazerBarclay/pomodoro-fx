package dev.jazer.pomodoroFX.widgets.button;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlayStopButton extends CanvasSwitchButton {

	public PlayStopButton(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public PlayStopButton(int x, int y, int size) {
		this(x, y, size, size);
	}

	@Override
	public void drawClicked() {
		GraphicsContext gc = getGraphicsContext2D();
		gc.setFill(Color.LIGHTGREEN);
		gc.fillPolygon(new double[]{2, (getWidth()/5)*4+3, 2}, new double[]{0, getHeight()/2, getHeight()}, 3);
	}

	@Override
	public void drawReleased() {
		GraphicsContext gc = getGraphicsContext2D();
		gc.setFill(Color.PALEVIOLETRED);
		gc.fillRect(0, 0, getWidth(), getHeight());
	}

}
