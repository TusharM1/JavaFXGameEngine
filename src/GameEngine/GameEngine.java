package GameEngine;

import GameEngine.GameObjects.GameObject;
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

public class GameEngine {

	private IGameLoop game;

	private int width;
	private int height;

	private Canvas canvas;
	private GraphicsContext graphicsContext;

	private Scene scene;
	private Group group;
	private GameLoop gameLoop;

	private ArrayList<GameObject> renderQueue;

	private boolean[] keyboard;

	private MouseEvent mouse;
	private Rectangle mouseHitBox;

	public GameEngine(IGameLoop game, int width, int height) {
		this.game = game;
		this.width = width;
		this.height = height;

		gameLoop = new GameLoop();
		renderQueue = new ArrayList<>();

		keyboard = new boolean[65536];
		mouseHitBox = new Rectangle(1, 1);
	}

	public void init() {
		try {
			canvas = new Canvas(width, height);
			graphicsContext = canvas.getGraphicsContext2D();
		} catch (Exception e) {
			e.printStackTrace();
		}

		group = new Group(canvas);
		scene = new Scene(group);

		scene.widthProperty().addListener((observable, oldValue, newValue) -> {
			width = newValue.intValue();
			canvas.setWidth(width);
		});
		scene.heightProperty().addListener((observable, oldValue, newValue) -> {
			height = newValue.intValue();
			canvas.setHeight(height);
		});

		scene.addEventHandler(KeyEvent.ANY, event -> keyboard[event.getCode().getCode()] = event.getEventType().getName().equals("KEY_PRESSED"));
		scene.addEventHandler(MouseEvent.ANY, event -> {
			mouse = event;
			mouseHitBox.setX(event.getX());
			mouseHitBox.setY(event.getY());
		});

		GameObject.setGameEngine(this);
	}

	public Scene getScene() {
		return scene;
	}

	public Group getGroup() {
		return group;
	}

	public void start() {
		gameLoop.start();
	}

	public void moveToFront(GameObject gameObject) {
		renderQueue.remove(gameObject);
		renderQueue.add(gameObject);
	}

	public ArrayList<GameObject> getRenderQueue() {
		return renderQueue;
	}

	public GraphicsContext getGraphicsContext() {
		return graphicsContext;
	}

	private class GameLoop extends AnimationTimer {
		@Override
		public void handle(long now) {
			graphicsContext.setFill(Color.CYAN);
			graphicsContext.fillRect(0, 0, width, height);

			// Despite IntelliSense saying otherwise, DO NOT CONVERT THIS INTO A FOR-EACH LOOP. Things will break
			for (int i = 0; i < renderQueue.size(); i++)
				renderQueue.get(i).update();

			game.updateFrame();

			for (GameObject gameObject : renderQueue)
				gameObject.draw();
		}
	}

	public IGameLoop getGame() {
		return game;
	}

	public void setGame(IGameLoop game) {
		this.game = game;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public void setGraphicsContext(GraphicsContext graphicsContext) {
		this.graphicsContext = graphicsContext;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public GameLoop getGameLoop() {
		return gameLoop;
	}

	public void setGameLoop(GameLoop gameLoop) {
		this.gameLoop = gameLoop;
	}

	public void setRenderQueue(ArrayList<GameObject> renderQueue) {
		this.renderQueue = renderQueue;
	}

	public boolean[] getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(boolean[] keyboard) {
		this.keyboard = keyboard;
	}

	public MouseEvent getMouse() {
		return mouse;
	}

	public void setMouse(MouseEvent mouse) {
		this.mouse = mouse;
	}

	public Rectangle getMouseHitBox() {
		return mouseHitBox;
	}

	public void setMouseHitBox(Rectangle mouseHitBox) {
		this.mouseHitBox = mouseHitBox;
	}
}
