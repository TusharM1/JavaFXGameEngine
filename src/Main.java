import GameEngine.GameEngine;
import GameEngine.IGameLoop;
import Objects.PlayerShip;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application implements IGameLoop {

//    EllipseGameObject ellipseGameObject;
    PlayerShip playerShip;

    @Override
    public void start(Stage primaryStage) {
        GameEngine gameEngine = new GameEngine(this, 640, 480);
        gameEngine.init();
        gameEngine.createKeyListener();

//        ellipseGameObject = new EllipseGameObject(200, 200, 100, 100);
        playerShip = new PlayerShip(200, 200, 100, 100);

        primaryStage.setTitle("Asteroids Remade");
        primaryStage.setScene(gameEngine.getScene());
        primaryStage.show();

        gameEngine.start();
    }

	@Override
	public void updateFrame(ArrayList<GameEngine.GameObject> renderQueue) {
		for (GameEngine.GameObject gameObject : renderQueue) {
			System.out.println("Hello");
		}
	}

    public static void main(String[] args) {
        launch(args);
    }
}
