package dev.jazer.pomodoroFX.widgets;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CanvasProgressBar extends Canvas {

	int count, complete;
	
	public CanvasProgressBar(int x, int y, int width, int height) {
		super(width, height);
		setLayoutX(x);
		setLayoutY(y);
		reset();
	}
	
	private void clear() {
		getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	private void draw() {
		int size = 6, padding = 4;
		GraphicsContext gc = getGraphicsContext2D();
		if (count == 0) {gc.setStroke(Color.BLACK); gc.strokeOval(getWidth()/2-padding/2, getHeight()/2-size/2, size, size);}
		gc.setStroke(Color.BLACK);
		gc.setFill(Color.color(0.168, 0.188, 0.231));
		
		for (int i = 0; i < count; i++) {
			gc.strokeOval(getWidth()/2-(count*(size+padding))/2+i*(size+padding)+padding/2, getHeight()/2-size/2, size, size);
		}
		for (int i = 0; i < complete; i++) {
			gc.fillOval(getWidth()/2-(count*(size+padding))/2+i*(size+padding)+padding/2, getHeight()/2-size/2, size, size);
		}
	}
	
	public void increment() {
		if (count != complete) complete++;
		else count++;
		clear();
		draw();
	}
	
	public void reset() {
		clear();
		count = 0;
		complete = 0;
		draw();
	}
	
}
