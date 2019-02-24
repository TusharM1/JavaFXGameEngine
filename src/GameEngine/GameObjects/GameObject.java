package GameEngine.GameObjects;

import GameEngine.GameEngine;
import GameEngine.Utilities.Vector2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

// TODO convert this class into an interface
// TODO comment all of this code at some point
// TODO add support for linear velocity and acceleration

// Possibly only have Polygon, Ellipse, Image and maybe Path Game Objects. Try making the rectangle Game Object into a polygon
// Also try adding translation and rotation transformations to the original polygon points and remove the extra translation and rotation code.
// But how did the collision detection work for rotated objects in the old code? figure this out.

public abstract class GameObject {

	private String objectName, objectType;

	// Add implementation for: acceleration, friction, velocity, maximumVelocityX, and maximumVelocityY
//	private double rotation, rotationSpeed, accelerationAngle,
//			acceleration, accelerationX, accelerationY,
//			friction, frictionX, frictionY,
//			velocity, velocityX, velocityY,
//			maximumVelocity, maximumVelocityX, maximumVelocityY,
//			locationX, locationY, mass;

	private double rotation, rotationSpeed, locationX, locationY;
//			acceleration, accelerationX, accelerationY,
//			friction, frictionX, frictionY,
//			velocity, velocityX, velocityY,
//			maximumVelocity, maximumVelocityX, maximumVelocityY,
//			 mass;

	private boolean isVisible;

	private static GameEngine gameEngine;

	Vector2D acceleration;
	Vector2D friction;
	Vector2D maximumVelocity;
	Vector2D velocity;

	public GameObject() {
		isVisible = true;

		acceleration = new Vector2D();
		friction = new Vector2D();
		maximumVelocity = new Vector2D();
		velocity = new Vector2D();
	}

	public void update() {
//		setVelocity(velocity + acceleration);
		setRotation(rotation + rotationSpeed);
//		if (friction > 0) {
//			setFriction(friction);
//			if (Math.abs(velocity) > Math.abs(friction))
//				setVelocity(velocity - friction);
//			else
//				setVelocity(0);
//		}
//		if (maximumVelocity > 0) {
//			setMaximumVelocity(maximumVelocity);
//			if (Math.abs(velocity) > Math.abs(maximumVelocity))
//				setVelocity(maximumVelocityX);
//		}
//		setLocation(locationX + velocityX, locationY + velocityY);
		addLocation(velocity.getComponentX(), velocity.getComponentY()); // TODO overload function with Vector parameter
//		System.out.println(velocity);
	}

	public final void draw() {
		if (isVisible) {
			gameEngine.getGraphicsContext().save();
			drawObject();
			gameEngine.getGraphicsContext().restore();
		}
	}

	// TODO Cleanup this dumpster fire of getters and setters

	// ROTATION
	public double getRotation() { return rotation; }
	public void setRotation(double rotation) { this.rotation = (rotation + 360) % 360; }
	public double getRotationSpeed() { return rotationSpeed; }
	public void setRotationSpeed(double rotationSpeed) { this.rotationSpeed = rotationSpeed; }

//	public double getAccelerationAngle() { return accelerationAngle; }
//	public void setAccelerationAngle(double accelerationAngle) { this.accelerationAngle = accelerationAngle; }
//

	// Acceleration
	public void setAcceleration(double accelerationX, double accelerationY, double offset) {
		this.acceleration.setCartesianCoordinates(accelerationX, accelerationY, offset);
	}

	public void setAcceleration(double acceleration, double angle) {
		this.acceleration.setPolarCoordinates(acceleration, angle);
	}

	// Velocity
	public void setVelocity(double velocityX, double velocityY, double offset) {
//		System.out.println(velocityX + " " + velocityY + " " + offset);
		this.velocity.setCartesianCoordinates(velocityX, velocityY, offset);
//		System.out.println(velocity);
	}

	public void setVelocity(double velocity, double angle) {
		this.velocity.setPolarCoordinates(velocity, angle);
	}


	//	// ACCELERATION
//	public double getAccelerationX() { return accelerationX; }
//	public void setAccelerationX(double accelerationX) {
//		this.accelerationX = accelerationX;
//		this.acceleration = Math.hypot(accelerationX, this.accelerationY);
//		this.accelerationAngle = rotation + Math.atan2(this.accelerationY, accelerationX);
//	}
//	public double getAccelerationY() { return accelerationY; }
//	public void setAccelerationY(double accelerationY) {
//		this.accelerationY = accelerationY;
//		this.acceleration = Math.hypot(this.accelerationX, accelerationY);
//		this.accelerationAngle = rotation + Math.atan2(accelerationY, this.accelerationX);
//	}
//	public double getAcceleration() { return acceleration; }
//	public void setAcceleration(double acceleration) {
//		this.acceleration = acceleration;
//		this.accelerationX = acceleration * Math.sin(rotation);
//		this.accelerationY = acceleration * Math.cos(rotation);
//		this.accelerationAngle = rotation + Math.atan2(this.accelerationY, this.accelerationX);
//	}
//	public void setAcceleration(double accelerationX, double accelerationY) {
//		this.acceleration = Math.hypot(accelerationX, accelerationY);
//		this.accelerationX = accelerationX;
//		this.accelerationY = accelerationY;
////		this.accelerationAngle = rotation + Math.toDegrees(Math.atan2(accelerationX, accelerationY));
////		System.out.println(accelerationAngle);
//	}
//
//
//	// VELOCITY
//	public double getVelocityX() { return velocityX; }
//	public void setVelocityX(double velocityX) {
//		this.velocityX = velocityX;
//		this.velocity = Math.hypot(velocityX, this.velocityY);
//	}
//	public double getVelocityY() { return velocityY; }
//	public void setVelocityY(double velocityY) {
//		this.velocityY = velocityY;
//		this.velocity = Math.hypot(this.velocityX, velocityY);
//	}
//	public double getVelocity() { return velocity; }
//	public void setVelocity(double velocity) {
//		if (velocity == 0)
//			this.velocityX = this.velocityY = this.velocity = 0;
//		else {
//			this.velocityX = velocity * accelerationX / acceleration;
//			this.velocityY = velocity * accelerationY / acceleration;
//			this.velocity = velocity;
//		}
//	}
//	public void setVelocity(double velocityX, double velocityY) {
////		this.velocity = Math.hypot(velocityX, velocityY);
//		this.velocityX = velocityX * Math.sin(Math.toRadians(rotation));
//		this.velocityY = velocityY * Math.cos(Math.toRadians(rotation));
//		System.out.println(Math.sin(Math.toRadians(rotation)));
//	}
//
//
//	// FRICTION
//	public double getFrictionX() { return frictionX; }
//	public void setFrictionX(double frictionX) {
//		this.frictionX = frictionX;
//		this.friction = Math.hypot(frictionX, this.frictionY);
//	}
//	public double getFrictionY() { return frictionY; }
//	public void setFrictionY(double frictionY) {
//		this.frictionY = frictionY;
//		this.friction = Math.hypot(this.frictionX, frictionY);
//	}
//	public double getFriction() { return friction; }
//	public void setFriction(double friction) {
//		this.friction = friction;
//		this.frictionX = -friction * velocityX / velocity;
//		this.frictionY = -friction * velocityY / velocity;
//	}
//	public void setFriction(double frictionX, double frictionY) {
//		this.friction = Math.hypot(frictionX, frictionY);
//		this.frictionX = frictionX;
//		this.frictionY = frictionY;
//	}
//
//
//	// MAX VELOCITY
//	public double getMaximumVelocityX() { return maximumVelocityX; }
//	public void setMaximumVelocityX(double maximumVelocityX) {
//		this.maximumVelocityX = maximumVelocityX;
//		this.maximumVelocity = Math.hypot(maximumVelocityX, this.maximumVelocityY);
//	}
//	public double getMaximumVelocityY() { return maximumVelocityY; }
//	public void setMaximumVelocityY(double maximumVelocityY) {
//		this.maximumVelocityY = maximumVelocityY;
//		this.maximumVelocity = Math.hypot(this.maximumVelocityX, maximumVelocityY);
//	}
//	public double getMaximumVelocity() { return maximumVelocity; }
//	public void setMaximumVelocity(double maximumVelocity) {
//		this.maximumVelocity = maximumVelocity;
//		this.maximumVelocityX = maximumVelocity * velocityX / velocity;
//		this.maximumVelocityY = maximumVelocity * velocityY / velocity;
//
//	}
//	public void setMaximumVelocity(double maximumVelocityX, double maximumVelocityY) {
//		this.maximumVelocity = Math.hypot(maximumVelocityX, maximumVelocityY);
//		this.maximumVelocityX = maximumVelocityX;
//		this.maximumVelocityY = maximumVelocityY;
//	}

	// LOCATION
	public double getLocationX() { return locationX; }
	public void setLocationX(double locationX) { this.locationX = locationX; }
	public double getLocationY() { return locationY; }
	public void setLocationY(double locationY) { this.locationY = locationY; }
	public void setLocation(double locationX, double locationY) {
		this.locationX = locationX;
		this.locationY = locationY;
	}
	public void addLocation(double locationX, double locationY) {
		this.locationX += locationX;
		this.locationY += locationY;
	}

	// EXTRAS
//	public double getMass() { return mass; }
//	public void setMass(double mass) { this.mass = mass; }

	public boolean isVisible() { return isVisible; }
	public void setVisibility(boolean visible) { isVisible = visible; }

	protected abstract void drawObject();
	public abstract Shape getHitBox();
	public static void setGameEngine(GameEngine gameEngine) { GameObject.gameEngine = gameEngine; }
	public static GameEngine getGameEngine() { return GameObject.gameEngine; }
	public static GraphicsContext getGraphicsContext() { return gameEngine.getGraphicsContext(); }

	public String getObjectName() { return objectName; }
	public void setObjectName(String objectName) { this.objectName = objectName; }
	public String getObjectType() { return objectType; }
	public void setObjectType(String objectType) { this.objectType = objectType; }

//	@Override
//	public String toString() { return String.format("[%s: %s]", objectName, objectType); }
//	public String toString() { return String.format("%s %s %s %s %s", velocity, velocityX, velocityY, rotation, accelerationAngle); }

}