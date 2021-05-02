package dev.jazer.pomodoroFX.widgets.button;

import javafx.event.Event;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public abstract class CanvasButton extends Canvas {

	protected int width, height;
	protected GraphicsContext gc;
	private ClickEvent click, release;
	private boolean pressed;
	
	public CanvasButton(int x, int y, int width, int height) {
		super(width, height);
		
		this.width = width;
		this.height = height;
		this.pressed = false;
		this.gc = getGraphicsContext2D();

		setLayoutX(x);
		setLayoutY(y);
		
		drawReleased();
		setClickHandler();
	}

	public CanvasButton() {
		this(0, 0, 100, 100);
	}

	public abstract void drawClicked();
	
	public abstract void drawReleased();

	public void setOnClickHandler(ClickEvent e) {
		this.click = e;
	}
	
	public void onPress() {
		if(click != null) click.onClick();
	}

	public void setOnReleaseHandler(ClickEvent e) {
		this.release = e;
	}
	
	public void onRelease() {
		if(release != null) release.onClick();
	}
	
	public void setIsClicked(boolean clicked) {
		this.pressed = clicked;
		clear();
		if (pressed) {
			drawClicked();
		} else {
			drawReleased();
		}
	}
	
	public void clear() {
		gc.clearRect(0, 0, width, height);
	}
	
	private void setClickHandler() {
		setOnMousePressed((Event e) -> {
			pressed ^= true;
			clear();
			if (pressed) {
				drawClicked();
				onPress();
			} else {
				drawReleased();
				onRelease();
			}
		});
	}
	
}
