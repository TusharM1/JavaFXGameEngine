package Objects;

import CoreEngine.Dimension;
import CoreEngine.GameObjects.EllipseGameObject;
import CoreEngine.Location;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

public class DragCircle extends EllipseGameObject {

	double cursorInShapeX, cursorInShapeY;
	boolean selectedState;

	public DragCircle(Location location, Dimension dimension) {
		super(location, dimension);
		setColor(Color.valueOf("#FFa500FF"));
		setObjectName("DragCircle");
	}

	public boolean inTheWindow() {
		return getHitBox().getBoundsInParent().intersects(getCanvas().getLayoutBounds())
				|| getCanvas().getLayoutBounds().contains(getHitBox().getBoundsInParent());
	}

	@Override
	public void update() {
		setRotationSpeed((getGameEngine().getKeyboard()[KeyCode.LEFT.getCode()] ? -2 : 0) + (getGameEngine().getKeyboard()[KeyCode.RIGHT.getCode()] ? 2 : 0));

		// TODO Move this into the Game Object Class
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

//		System.out.println(!Shape.intersect(getGameEngine().bottomBound, getHitBox()).getBoundsInParent().isEmpty());

		if (intersectsTopBound())
			System.out.println("Top");
		if (intersectsBottomBound())
			System.out.println("Bottom");
		if (intersectsLeftBound())
			System.out.println("Left");
		if (intersectsRightBound())
			System.out.println("Right");

		super.update();
	}
}
