package Objects;

import CoreEngine.Dimension;
import CoreEngine.GameObjects.EllipseGameObject;
import CoreEngine.Location;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class BouncingBall extends EllipseGameObject {

//    double acceleration;

    public BouncingBall(Location location, Dimension dimension) {
        super(location, dimension);
        setColor(Color.valueOf("#2FA9FFFF"));
        setMaximumVelocity(-1);
        setFriction(0.01);
//        acceleration = 0.25;
        setAccelerationY(0.25);
    }

    @Override
    public void update() {
        setAcceleration((getGameEngine().getKeyboard()[KeyCode.RIGHT.getCode()] ? .1 : 0) + (getGameEngine().getKeyboard()[KeyCode.LEFT.getCode()] ? -.1 : 0), 0.25, 0);
//        setVelocityX((getGameEngine().getKeyboard()[KeyCode.RIGHT.getCode()] ? 5 : 0) + (getGameEngine().getKeyboard()[KeyCode.LEFT.getCode()] ? -5 : 0));

        if (getGameEngine().getKeyboard()[KeyCode.SPACE.getCode()])
            reset();

//        if (getGameEngine().getKeyboard()[KeyCode.UP.getCode()])
//            setVelocityY(-10);

//        System.out.println(inTheWindow());
//        System.out.println(!Shape.intersect(getGameEngine().bottomBound, getHitBox()).getBoundsInParent().isEmpty());
//        System.out.println("Top: " + intersectsTopBound());
//        System.out.println("Bottom: " + intersectsBottomBound());
//        System.out.println("Left: " + intersectsLeftBound());
//        System.out.println("Right: " + intersectsRightBound());

//        if (intersectsBottomBound()) {
//            if (getVelocityY() > acceleration) {
//                setVelocityY(getVelocityY() * -.825);
////                setVelocityY(0);
//            }
//            else {
//                setLocationY(getCanvas().getHeight() - getHeight() / 2);
//            }
//            System.out.println(getLocationY() + " " + getVelocityY() + " " + getAccelerationY() + " ");
//        }

        super.update();
    }

    @Override public void onIntersectTopBound() {
        setVelocityY(getVelocityY() * -.85);
        setLocationY(getHeight() / 2);
    }

    @Override
    public void onIntersectBottomBound() {
        setVelocityY(getVelocityY() * -.85);
        setLocationY(getCanvas().getHeight() - getHeight() / 2);
        if (getGameEngine().getKeyboard()[KeyCode.UP.getCode()])
            setVelocityY(-10);
//        System.out.println("d");
//        while (intersectsBottomBound())
//        setVelocityY(0);
//        if (getGameEngine().getKeyboard()[KeyCode.UP.getCode()])
//            setVelocityY(-10);
//            adjustLocation(0, -1);
//        System.out.println(getLocationY() + "\t" + getVelocityY());
//        if (getVelocityY() < 2 * acceleration) {
//            setLocationY(getCanvas().getHeight() - getHeight() / 2);
//            setVelocityY(0);
//        }
//        else
//        setVelocityY(getVelocityY() * -.5);
//        if (getVelocityY() > acceleration) {
//            setVelocityY(getVelocityY() * -.825);
//        }
//        else {
//            setLocationY(getCanvas().getHeight() - getHeight() / 2);
//        }
    }

    @Override public void onIntersectLeftBound() {
        setVelocityX(getVelocityX() * -.85);
        setLocationX(getWidth() / 2);
    }

    @Override public void onIntersectRightBound() {
        if (!getGameEngine().getKeyboard()[KeyCode.RIGHT.getCode()])
            setVelocityX(getVelocityX() * -.85);
        else
            setVelocityX(0);
        setLocationX(getCanvas().getWidth() - getWidth() / 2);
    }

}
