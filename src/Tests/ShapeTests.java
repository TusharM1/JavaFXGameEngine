package Tests;

import GameEngine.GameEngine;
import GameEngine.IGameLoop;
import Objects.DragSquare;
import Objects.DragStar;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ShapeTests extends Application implements IGameLoop {

	GameEngine gameEngine;
	ArrayList<GameEngine.GameObject> renderQueue;

	@Override
	public void init() throws Exception {
		super.init();

		gameEngine = new GameEngine(this, 640, 480);
		gameEngine.init();

		renderQueue = gameEngine.getRenderQueue();
	}

	DragStar dragStar;
	DragSquare dragSquare;

	@Override
	public void start(Stage primaryStage) {

		dragStar = new DragStar(200, 200, new double[][]{{0, -75}, {25, -25}, {75, 0}, {25, 25}, {0, 75}, {-25, 25}, {-75, 0}, {-25, -25}}, 0, -75);
		dragSquare = new DragSquare(200, 200, 100, 100);

		renderQueue.add(dragStar);
		renderQueue.add(dragSquare);

		primaryStage.setTitle("Asteroids Remade");
		primaryStage.setScene(gameEngine.getScene());
		primaryStage.show();

		gameEngine.start();
	}

	Group subGroup;

	@Override
	public void updateFrame() {
//		if(((Path) Shape.intersect(dragSquare.rectangle, dragStar.polygon)).getElements().size() > 0)
//			System.out.println("Collision");

//		Group group = gameEngine.getGroup();
//		subGroup = new Group();
//		for (GameEngine.GameObject gameObject : renderQueue)
//			subGroup.getChildren().add(gameObject.getNode());
//		group.getChildren().add(subGroup);
	}

}
