import GameEngine.GameEngine;
import GameEngine.IGameLoop;
import Objects.PlayerShip;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application implements IGameLoop {

	GameEngine gameEngine;
	ArrayList<GameEngine.GameObject> renderQueue;

	PlayerShip playerShip;

	@Override
	public void init() throws Exception {
		super.init();

		gameEngine = new GameEngine(this, 640, 480);
		gameEngine.init();

		renderQueue = gameEngine.getRenderQueue();
	}

	@Override
    public void start(Stage primaryStage) {

		playerShip = new PlayerShip(200, 200, 100, 100);
		renderQueue.add(playerShip);

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
