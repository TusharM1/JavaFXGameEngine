import GameEngine.GameEngine;
import GameEngine.GameObjects.GameObject;
import GameEngine.IGameLoop;
import Objects.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application implements IGameLoop {

	GameEngine gameEngine;
	ArrayList<GameObject> renderQueue;

	@Override
	public void init() throws Exception {
		super.init();

		gameEngine = new GameEngine(this, 640, 480);

		renderQueue = gameEngine.getRenderQueue();
	}

	@Override
    public void start(Stage primaryStage) {

		renderQueue.add(new SpaceShip(200, 200, 100, 100));
//		renderQueue.add(new SpaceShip(0, 0, 100, 100));
//		renderQueue.add(new FloatingBall(200, 200, 50, 50));
//		renderQueue.add(new FloatingBall(0, 0, 50, 50));
//		renderQueue.add(new Starfish(200, 200, new double[][]{{0, -75}, {25, -25}, {75, 0}, {25, 25}, {0, 75}, {-25, 25}, {-75, 0}, {-25, -25}}, 0, -75));
//		renderQueue.add(new Starfish(0, 0, new double[][]{{0, -75}, {25, 25}, {0, 15}, {-25, 25}}, 0, 25));
//		renderQueue.add(new Starfish(0, 0, new double[][]{{0, -50}, {50, 0}, {0, 50}, {-50, 0}}, 0, 0));
//		renderQueue.add(new GravityBall(200, 200, 50, 50));
//		renderQueue.add(new DragSquare(200, 200, 100, 100));
//		renderQueue.add(new DragStar(200, 200, new double[][]{{0, -75}, {25, -25}, {75, 0}, {25, 25}, {0, 75}, {-25, 25}, {-75, 0}, {-25, -25}}, 0, -75));

        primaryStage.setTitle("Asteroids Remade");
        primaryStage.setScene(gameEngine.getScene());
        primaryStage.show();

        gameEngine.start();
    }

    Group subGroup;

	@Override
	public void updateFrame() {
//		System.out.println(renderQueue.get(0));
//		Group group = gameEngine.getGroup();
//		subGroup = new Group();
//		for (GameEngine.GameObject gameObject : renderQueue)
//			subGroup.getChildren().add(gameObject.getNode());
//		group.getChildren().add(subGroup);
	}

    public static void main(String[] args) {
        launch(args);
    }
}
