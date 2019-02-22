package Objects;

import GameEngine.GameObjects.RectangleGameObject;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

public class DragSquare extends RectangleGameObject {

	double cursorInShapeX, cursorInShapeY;
	boolean selectedState;

	public DragSquare(double locationX, double locationY, double width, double height) {
		super(locationX, locationY, width, height);
		setColor(Color.valueOf("#FFa500FF"));
		setObjectName("DragSquare");
	}

	@Override
	public void update() {
		setRotationSpeed((getGameEngine().getKeyboard()[KeyCode.LEFT.getCode()] ? -2 : 0) + (getGameEngine().getKeyboard()[KeyCode.RIGHT.getCode()] ? 2 : 0));

		MouseEvent mouseEvent;
		if ((mouseEvent = getGameEngine().getMouse()) != null) {
			if (((Path) Shape.intersect(getGameEngine().getMouseHitBox(), getHitBox())).getElements().size() > 0 && mouseEvent.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
				cursorInShapeX = mouseEvent.getX() - getLocationX();
				cursorInShapeY = mouseEvent.getY() - getLocationY();
				getGameEngine().moveToFront(this);
				selectedState = true;
			}
			if (selectedState && mouseEvent.getEventType().equals(MouseEvent.MOUSE_DRAGGED))
				setLocation(mouseEvent.getX() - cursorInShapeX, mouseEvent.getY() - cursorInShapeY);
			if (!mouseEvent.isPrimaryButtonDown())
				selectedState = false;
		}

		super.update();
	}
}
