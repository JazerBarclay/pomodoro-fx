package dev.jazer.pomodoroFX.widgets.button;

import javafx.scene.paint.Color;

public class FastForwardButton extends CanvasButton {

	public FastForwardButton(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public FastForwardButton(int x, int y, int size) {
		this(x, y, size, size);
	}

	public FastForwardButton(int x, int y) {
		this(x, y, 25, 25);
	}

	public FastForwardButton() {
		this(0, 0);
	}
	
	@Override
	public void drawClicked() {
		gc.setFill(Color.LIGHTBLUE);

		gc.fillPolygon(new double[]{0, (width/5)*4, 0}, new double[]{0, height/2, height}, 3);
		gc.strokePolygon(new double[]{0, (width/5)*4, 0}, new double[]{0, height/2, height}, 3);
		
		gc.fillRect((width/5)*4, 0, 8, height);
		gc.strokeRect((width/5)*4, 0, 8, height);
	}

	@Override
	public void drawReleased() {
		drawClicked();
	}

}
