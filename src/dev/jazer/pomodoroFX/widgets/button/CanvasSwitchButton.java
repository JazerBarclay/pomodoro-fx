package dev.jazer.pomodoroFX.widgets.button;

import javafx.event.Event;

public abstract class CanvasSwitchButton extends CanvasClickButton {

	private boolean toggled;
	private ClickEvent release;
	
	public CanvasSwitchButton(int x, int y, int width, int height) {
		super(x, y, width, height);
		toggled = false;
	}
	
	@Override
	protected void setClickEvent() {
		setOnMouseClicked((Event e) -> {
			toggled ^= true;
			clear();
			if (toggled) {
				drawReleased();
				if (press != null) press.onClick();
			} else {
				drawClicked();
				if (release != null) release.onClick();
			}
		});
	}

	public void setOnReleaseHandler(ClickEvent event) {
		this.release = event;
	}
	
	public void setIsClicked(boolean toggled) {
		this.toggled = toggled;
		clear();
		if (toggled) {
			drawReleased();
		} else {
			drawClicked();
		}
	}
	
	public abstract void drawReleased();
	
}
