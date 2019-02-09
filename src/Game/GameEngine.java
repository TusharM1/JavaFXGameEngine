package Game;

import Objects.GameObjects.GameObject;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class GameEngine {

	IGameLoop game;

	int width;
	int height;

	boolean[] keyboard;

	Canvas canvas;
	GraphicsContext graphicsContext;

	Scene scene;
	GameLoop gameLoop;

	public GameEngine(IGameLoop game, int width, int height) {
		this.game = game;
		this.width = width;
		this.height = height;
	}

	public void init() {
		try {
			canvas = new Canvas(width, height);
			graphicsContext = canvas.getGraphicsContext2D();
			GameObject.setGraphicsContext(graphicsContext);
		} catch (Exception e) {
			e.printStackTrace();
		}

		scene = new Scene(new Group(canvas));

		gameLoop = new GameLoop();

		scene.widthProperty().addListener((observable, oldValue, newValue) -> {
			width = newValue.intValue();
			canvas.setWidth(width);
		});
		scene.heightProperty().addListener((observable, oldValue, newValue) -> {
			height = newValue.intValue();
			canvas.setHeight(height);
		});
	}

	public void createKeyListener() {
		keyboard = new boolean[65536];
		scene.addEventHandler(KeyEvent.ANY, event -> keyboard[event.getCode().getCode()] = event.getEventType().getName().equals("KEY_PRESSED"));
	}

	public Scene getScene() {
		return scene;
	}

	public void start() {
		gameLoop.start();
	}

	private class GameLoop extends AnimationTimer {
		@Override
		public void handle(long now) {
			graphicsContext.setFill(Color.CYAN);
			graphicsContext.fillRect(0, 0, width, height);

			game.drawFrame();
		}
	}

}
