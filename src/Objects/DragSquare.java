package Objects;

import GameEngine.GameEngine;
import GameEngine.GameObjects.RectangleGameObject;
import javafx.scene.input.MouseEvent;

public class DragSquare extends RectangleGameObject {

	public DragSquare(double locationX, double locationY, double width, double height) {
		super(locationX, locationY, width, height);
	}

	@Override
	public void update() {
		MouseEvent mouseEvent;
//		if ((mouseEvent = GameEngine.mouse) != null)
//			System.out.println(mouseEvent.getX() + " " + mouseEvent.getY() + " " + mouseEvent.getEventType() + " " + mouseEvent.getButton());

		super.update();
	}
}
