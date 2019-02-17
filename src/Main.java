import GameEngine.GameEngine;
import GameEngine.IGameLoop;
import Objects.*; // Do not remove until testing is over
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application implements IGameLoop {

	GameEngine gameEngine;
	ArrayList<GameEngine.GameObject> renderQueue;

	@Override
	public void init() throws Exception {
		super.init();

		gameEngine = new GameEngine(this, 640, 480);
		gameEngine.init();

		renderQueue = gameEngine.getRenderQueue();
	}

	@Override
    public void start(Stage primaryStage) {

//		renderQueue.add(new SpaceShip(200, 200, 100, 100));
//		renderQueue.add(new SpaceShip(0, 0, 100, 100));
//		renderQueue.add(new FloatingBall(200, 200, 50, 50));
//		renderQueue.add(new FloatingBall(0, 0, 50, 50));
//		renderQueue.add(new Starfish(200, 200, new double[][]{{0, -75}, {25, -25}, {75, 0}, {25, 25}, {0, 75}, {-25, 25}, {-75, 0}, {-25, -25}}, 0, 0));
		renderQueue.add(new Starfish(0, 0, new double[][]{{0, -50}, {50, 0}, {0, 50}, {-50, 0}}, 0, 0));
//		renderQueue.add(new GravityBall(200, 200, 100, 100));
//		renderQueue.add(new DragSquare(200, 200, 100, 100));

        primaryStage.setTitle("Asteroids Remade");
        primaryStage.setScene(gameEngine.getScene());
        primaryStage.show();

        gameEngine.start();
    }

	@Override
	public void updateFrame() {
//		for (GameEngine.GameObject gameObject : renderQueue) {
//			System.out.println("Hello");
//		}
//		System.out.println("Working");
	}

    public static void main(String[] args) {
        launch(args);
    }
}
