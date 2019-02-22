package Tests;

import GameEngine.GameEngine;
import GameEngine.GameObjects.GameObject;
import GameEngine.IGameLoop;
import Objects.DragCircle;
import Objects.DragSquare;
import Objects.DragStar;
import javafx.application.Application;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DragShapesTest extends Application implements IGameLoop {

	GameEngine gameEngine;
	ArrayList<GameObject> renderQueue;

	@Override
	public void init() throws Exception {
		super.init();

		gameEngine = new GameEngine(this, 640, 480);
		gameEngine.init();

		renderQueue = gameEngine.getRenderQueue();
	}

	DragStar dragStar;
	DragSquare dragSquare;
	DragCircle dragCircle;

	@Override
	public void start(Stage primaryStage) {
		dragStar = new DragStar(200, 200, new double[][]{{0, -75}, {25, -25}, {75, 0}, {25, 25}, {0, 75}, {-25, 25}, {-75, 0}, {-25, -25}}, 0, -75);
		dragSquare = new DragSquare(200, 200, 100, 100);
		dragCircle = new DragCircle(300, 300, 50, 50);

		renderQueue.add(dragStar);
		renderQueue.add(dragSquare);
		renderQueue.add(dragCircle);

		primaryStage.setTitle("Asteroids Remade");
		primaryStage.setScene(gameEngine.getScene());
		primaryStage.show();

		gameEngine.start();
	}

//	Group subGroup;

	@Override
	public void updateFrame() {
		for (int i = 0; i < renderQueue.size(); i++) {
			for (int j = i + 1; j < renderQueue.size(); j++) {
				if (((Path) Shape.intersect(renderQueue.get(i).getHitBox(), renderQueue.get(j).getHitBox())).getElements().size() > 0) {
					System.out.println("Collision");
				}
			}
		}
//		if(((Path) Shape.intersect(dragSquare.rectangle, dragStar.polygon)).getElements().size() > 0)
//			System.out.println("Collision");

//		Group group = gameEngine.getGroup();
//		subGroup = new Group();
//		for (GameEngine.GameObject gameObject : renderQueue)
//			subGroup.getChildren().add(gameObject.getNode());
//		group.getChildren().add(subGroup);
	}

}
