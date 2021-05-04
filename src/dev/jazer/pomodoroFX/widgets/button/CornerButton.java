package dev.jazer.pomodoroFX.widgets.button;

import javafx.scene.paint.Color;

public class CornerButton extends CanvasClickButton {

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
		gc.setFill(Color.color(0.168, 0.188, 0.231));
		
		gc.fillPolygon(new double[]{getWidth(), getWidth(), 0}, new double[]{0, getHeight(), getHeight()}, 3);
//		gc.strokePolygon(new double[]{width, width, 0}, new double[]{0, height, height}, 3);
	}
	
}
