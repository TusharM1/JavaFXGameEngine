package CoreEngine;

import CoreEngine.GameObjects.GameObject;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.TreeSet;

public class GameEngine {

	private IGameLoop game;

	private double width;
	private double height;

	private Canvas canvas;
	private GraphicsContext graphicsContext;

	private Scene scene;
	private Group group;
	private GameLoop gameLoop;

	private ArrayList<GameObject> renderQueue;
	private TreeSet<Layer> layers;

	private boolean[] keyboard;

	private MouseEvent mouse;
	private Rectangle mouseHitBox;

	public GameEngine(IGameLoop game, double width, double height) {
		this.game = game;
		this.width = width;
		this.height = height;

		gameLoop = new GameLoop();
		renderQueue = new ArrayList<>();
		layers = new TreeSet<>();

		keyboard = new boolean[65536];
		mouseHitBox = new Rectangle(1, 1);

		try {
			canvas = new Canvas(width, height);
			graphicsContext = canvas.getGraphicsContext2D();
		} catch (Exception e) {
			e.printStackTrace();
		}

		group = new Group(canvas);
		scene = new Scene(group);

		scene.widthProperty().addListener((observable, oldValue, newValue) -> {
			this.width = newValue.doubleValue();
			canvas.setWidth(this.width);
		});
		scene.heightProperty().addListener((observable, oldValue, newValue) -> {
			this.height = newValue.doubleValue();
			canvas.setHeight(this.height);
		});

		scene.addEventHandler(KeyEvent.ANY, event -> keyboard[event.getCode().getCode()] = event.getEventType().getName().equals("KEY_PRESSED"));
		scene.addEventHandler(MouseEvent.ANY, event -> {
			mouse = event;
			mouseHitBox.setX(event.getX());
			mouseHitBox.setY(event.getY());
		});

		GameObject.setGameEngine(this);
	}

	public void addLayer(Layer layer) {
		layers.add(layer);
	}

	public TreeSet<Layer> getLayers() {
		return layers;
	}

	private class GameLoop extends AnimationTimer {
		@Override
		public void handle(long now) {
			graphicsContext.setFill(Color.CYAN);
			graphicsContext.fillRect(0, 0, width, height);

			for (Layer layer : layers)
				layer.updateAll();

			game.updateFrame();

			for (Layer layer : layers)
				layer.renderAll();
		}
	}

	public void start() { gameLoop.start(); }

	public void moveToFront(GameObject gameObject) {
		renderQueue.remove(gameObject);
		renderQueue.add(gameObject);
	}

	public IGameLoop getGame() { return game; }
	public void setGame(IGameLoop game) { this.game = game; }

	public double getWidth() { return width; }
	public void setWidth(double width) { this.width = width; }
	public double getHeight() { return height; }
	public void setHeight(double height) { this.height = height; }
	public void setSize(double width, double height) { this.width = width; this.height = height; }

	public Canvas getCanvas() { return canvas; }
	public void setCanvas(Canvas canvas) { this.canvas = canvas; this.graphicsContext = canvas.getGraphicsContext2D(); }

	public GraphicsContext getGraphicsContext() { return graphicsContext; }
	public void setGraphicsContext(GraphicsContext graphicsContext) { this.graphicsContext = graphicsContext; this.canvas = graphicsContext.getCanvas(); }

	public Scene getScene() {
		return scene;
	}
	public void setScene(Scene scene) { this.scene = scene; }

	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) { this.group = group; }

	public GameLoop getGameLoop() { return gameLoop; }
	public void setGameLoop(GameLoop gameLoop) { this.gameLoop = gameLoop; }

	public ArrayList<GameObject> getRenderQueue() { return renderQueue; }
	public void setRenderQueue(ArrayList<GameObject> renderQueue) { this.renderQueue = renderQueue; }

	public boolean[] getKeyboard() { return keyboard; }
	public void setKeyboard(boolean[] keyboard) { this.keyboard = keyboard; }

	public MouseEvent getMouse() { return mouse; }
	public void setMouse(MouseEvent mouse) { this.mouse = mouse; }

	public Rectangle getMouseHitBox() { return mouseHitBox; }
	public void setMouseHitBox(Rectangle mouseHitBox) { this.mouseHitBox = mouseHitBox; }

}
