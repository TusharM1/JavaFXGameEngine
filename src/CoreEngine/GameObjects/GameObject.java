package CoreEngine.GameObjects;

import CoreEngine.GameEngine;
import CoreEngine.Location;
import CoreEngine.Utilities.Vector2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

// TODO convert this class into an interface
// TODO comment all of this code at some point
// TODO convert all GameObjects to use Builder design pattern to simplify constructors

// Possibly only have Polygon, Ellipse, Image and maybe Path Game Objects. Try making the rectangle Game Object into a polygon
// Also try adding translation and rotation transformations to the original polygon points and remove the extra translation and rotation code.

public abstract class GameObject {

	//---------------------------- Fields ----------------------------//

	private static GameEngine gameEngine;

	Vector2D acceleration;
	Vector2D friction;
	Vector2D maximumVelocity;
	Vector2D velocity;

	Location location;

	private String objectName, objectType;

	private double rotation, rotationSpeed, mass;
	private boolean isVisible;

	//---------------------------- Constructor ----------------------------//

	public GameObject() {
		// Initialize Vectors
		acceleration = new Vector2D();
		friction = new Vector2D();
		maximumVelocity = new Vector2D();
		velocity = new Vector2D();

		// Set default Text
		objectName = "Object";
		objectType = "GameObject";

		// Move Object to Center of Canvas
		location = new Location(gameEngine.getWidth() / 2, gameEngine.getHeight() / 2);

		// Set Default Values of Fields
		rotation = 0;
		rotationSpeed = 0;

		isVisible = true;
	}

//	public abstract void initialize();

	//---------------------------- Update Object ----------------------------//

	public void update() {
		// Updates Rotation
		rotation += rotationSpeed;

		// Updates Rotation of Vectors
		friction.setAngle(velocity.getAngle() + 180);
		maximumVelocity.setAngle(velocity.getAngle());

		// Updates Velocity
		velocity.add(acceleration);

		// Adds Friction to Velocity (Friction must be positive)
		if (Math.abs(velocity.getMagnitude()) > Math.abs(friction.getMagnitude()))
			velocity.add(friction);
		else
			velocity.setMagnitude(0);

		// Caps Velocity to Max Velocity
		if (Math.abs(velocity.getMagnitude()) > Math.abs(maximumVelocity.getMagnitude()))
			velocity.setMagnitude(maximumVelocity.getMagnitude());

		// Updates Location based on Velocity
		adjustLocation(velocity);
	}

	public final void draw() {
		// Draws the object if visible, saves and restores canvas to draw
		if (isVisible) {
			gameEngine.getGraphicsContext().save();
			drawObject();
			gameEngine.getGraphicsContext().restore();
		}
	}

	//---------------------------- Reset Object ----------------------------//

	public void reset(double locationX, double locationY) {
		setLocation(locationX, locationY);
		setVelocity(0, 0, 0);
		setRotation(0);
	}

	public void reset() {
		reset(getGameEngine().getWidth() / 2.0, getGameEngine().getHeight() / 2.0);
	}

	//---------------------------- Getters and Setters ----------------------------//

	// ROTATION
	public double getRotation() { return rotation; }
	public double getRotationSpeed() { return rotationSpeed; }
	public void setRotation(double rotation) { this.rotation = (rotation + 360) % 360; }
	public void setRotationSpeed(double rotationSpeed) { this.rotationSpeed = rotationSpeed; }
	
	// ACCELERATION
	public double getAccelerationX() { return acceleration.getComponentX(); }
	public double getAccelerationY() { return acceleration.getComponentY(); }
	public void setAcceleration(double accelerationX, double accelerationY, double offset) { this.acceleration.setCartesianCoordinates(accelerationX, accelerationY, offset); }
	public void setAcceleration(double acceleration, double angle) { this.acceleration.setPolarCoordinates(acceleration, angle); }
	public void setAcceleration(double acceleration) { this.acceleration.setMagnitude(acceleration); }
	public void setAccelerationX(double accelerationX) { this.acceleration.setComponentX(accelerationX); }
	public void setAccelerationY(double accelerationY) { this.acceleration.setComponentY(accelerationY); }
	
	// VELOCITY
	public double getVelocityX() { return velocity.getComponentX(); }
	public double getVelocityY() { return velocity.getComponentY(); }
	public void setVelocity(double velocityX, double velocityY, double offset) { this.velocity.setCartesianCoordinates(velocityX, velocityY, offset); }
	public void setVelocity(double velocity, double angle) { this.velocity.setPolarCoordinates(velocity, angle); }
	public void setVelocity(double velocity) { this.velocity.setMagnitude(velocity); }
	public void setVelocityX(double velocityX) { this.velocity.setComponentX(velocityX); }
	public void setVelocityY(double velocityY) { this.velocity.setComponentY(velocityY); }
	
	// FRICTION
	public double getFrictionX() { return friction.getComponentX(); }
	public double getFrictionY() { return friction.getComponentY(); }
	public void setFriction(double frictionX, double frictionY, double offset) { this.friction.setCartesianCoordinates(frictionX, frictionY, offset); }
	public void setFriction(double friction, double angle) { this.friction.setPolarCoordinates(friction, angle); }
	public void setFriction(double friction) { this.friction.setMagnitude(friction); }
	public void setFrictionX(double frictionX) { this.friction.setComponentX(frictionX); }
	public void setFrictionY(double frictionY) { this.friction.setComponentY(frictionY); }
	
	// MAX VELOCITY
	public double getMaximumVelocityX() { return maximumVelocity.getComponentX(); }
	public double getMaximumVelocityY() { return maximumVelocity.getComponentY(); }
	public void setMaximumVelocity(double maximumVelocityX, double maximumVelocityY, double offset) { this.maximumVelocity.setCartesianCoordinates(maximumVelocityX, maximumVelocityY, offset); }
	public void setMaximumVelocity(double maximumVelocity, double angle) { this.maximumVelocity.setPolarCoordinates(maximumVelocity, angle); }
	public void setMaximumVelocity(double maximumVelocity) { this.maximumVelocity.setMagnitude(maximumVelocity); }
	public void setMaximumVelocityX(double maximumVelocityX) { this.maximumVelocity.setComponentX(maximumVelocityX); }
	public void setMaximumVelocityY(double maximumVelocityY) { this.maximumVelocity.setComponentY(maximumVelocityY); }

	// LOCATION
	public double getLocationX() { return location.getX(); }
	public double getLocationY() { return location.getY(); }
	public Location getLocation() { return location; }

	public void setLocationX(double locationX) { location.setX(locationX); }
	public void setLocationY(double locationY) { location.setY(locationY); }
	public void setLocation(Location location) { this.location = location; }
	public void setLocation(double locationX, double locationY) { location.setLocation(locationX, locationY); }

	public void adjustLocation(double deltaX, double deltaY) { location.adjustLocation(deltaX, deltaY); }
	public void adjustLocation(Vector2D velocity) { location.adjustLocation(velocity); }

	// EXTRAS
	public double getMass() { return mass; }
	public void setMass(double mass) { this.mass = mass; }

	public boolean isVisible() { return isVisible; }
	public void setVisibility(boolean visible) { isVisible = visible; }

	protected abstract void drawObject();
	public abstract Shape getHitBox();

	public static void setGameEngine(GameEngine gameEngine) { GameObject.gameEngine = gameEngine; }
	public static GameEngine getGameEngine() { return GameObject.gameEngine; }
	public static GraphicsContext getGraphicsContext() { return gameEngine.getGraphicsContext(); }
	public static Canvas getCanvas() { return gameEngine.getCanvas(); }

	public double getCenterInCanvasX() { return getCanvas().getWidth() / 2; }
	public double getCenterInCanvasY() { return getCanvas().getHeight() / 2; }

	public String getObjectName() { return objectName; }
	public void setObjectName(String objectName) { this.objectName = objectName; }
	public String getObjectType() { return objectType; }
	public void setObjectType(String objectType) { this.objectType = objectType; }

	public static boolean intersects(GameObject gameObject1, GameObject gameObject2) {
//		return ((Path) Shape.intersect(gameObject1.getHitBox(), gameObject2.getHitBox())).getElements().size() > 0;
		return !Shape.intersect(gameObject1.getHitBox(), gameObject2.getHitBox()).getBoundsInParent().isEmpty();
	}

	//---------------------------- To String ----------------------------//

	@Override
	public String toString() { return String.format("[%s: %s]", objectName, objectType); }

}