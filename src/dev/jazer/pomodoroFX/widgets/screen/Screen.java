package dev.jazer.pomodoroFX.widgets.screen;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javafx.scene.Node;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Screen extends Scene {

	private static double xOffset = 0;
	private static double yOffset = 0;
	
	private Stage window;
	private Pane pane;
	private Canvas background;
	
	private Node draggableElement;
	
	public Screen(Stage window, Pane pane) {
		super(pane);
		this.pane = pane;
		this.window = window;
		this.background = new Canvas(window.getWidth(), window.getHeight());
		
		GraphicsContext gc = background.getGraphicsContext2D();
		gc.setFill(Color.color(.164, .168, .172));
		gc.fillRect(0, 0, window.getWidth(), window.getHeight());
		add(background);
	}
	
	public Screen(Stage window) {
		super(new Pane());
		this.pane = (Pane) getRoot();
	}
	
	public void add(Node n) {
		pane.getChildren().add(n);
	}
	
	public void remove(Node n) {
		pane.getChildren().remove(n);
	}
	
	public void setBackground(Paint p) {
		GraphicsContext gc = background.getGraphicsContext2D();
		gc.setFill(p);
		gc.fillRect(0, 0, window.getWidth(), window.getHeight());
	}
	
	public void setBorder(Paint p) {
		GraphicsContext gc = background.getGraphicsContext2D();
		gc.setStroke(p);
		gc.setLineWidth(2);
		gc.strokeRect(0, 0, window.getWidth(), window.getHeight());
	}
	
	public void setDraggableElement(Node n) {
		if (n == null && draggableElement == null) return;
		if (n == null) {
			draggableElement.setOnMousePressed((MouseEvent e) -> {});
			draggableElement.setOnMouseDragged((MouseEvent e) -> {});
			return;
		}
		draggableElement = n;
		draggableElement.setOnMousePressed((MouseEvent event) -> {
			xOffset = window.getX() - event.getScreenX();
			yOffset = window.getY() - event.getScreenY();
		});

		draggableElement.setOnMouseDragged((MouseEvent event) -> {
			window.setX(event.getScreenX() + xOffset);
			window.setY(event.getScreenY() + yOffset);
		});
	}
	
	public void positionBottomRight() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		window.setX(width - window.getWidth());
		window.setY(height - window.getHeight() - 40);
	}
	
}
