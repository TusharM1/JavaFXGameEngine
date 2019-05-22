package CoreEngine.GameObjects;

import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

public class PathGameObject extends GameObject {

	Path hitBox;

	public PathGameObject() {
		ArcTo arcTo = new ArcTo();
		arcTo.setX(50.0f);
		arcTo.setY(50.0f);
		arcTo.setRadiusX(50.0f);
		arcTo.setRadiusY(50.0f);

		hitBox = new Path(new ArcTo(50F, 50F, 0, 200, 200, false, false));

	}

	@Override
	protected void drawObject() {

	}

	@Override
	public Shape getHitBox() {
		return hitBox;
	}
}
