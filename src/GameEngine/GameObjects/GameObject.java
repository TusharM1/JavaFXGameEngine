package GameEngine.GameObjects;

import GameEngine.GameEngine;
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
	private double rotation, rotationSpeed,
			acceleration, accelerationX, accelerationY,
			friction, frictionX, frictionY,
			velocity, velocityX, velocityY,
			maximumVelocity, maximumVelocityX, maximumVelocityY,
			locationX, locationY, mass;

	private boolean isVisible = true;

	private static GameEngine gameEngine;

	public void update() {
		setRotation(rotation + rotationSpeed);
//		setVelocity(velocity + acceleration);
		setVelocity(velocityX + accelerationX, velocityY + accelerationY);

		if (friction > 0) {
			setFriction(friction);
			if (Math.abs(velocity) > Math.abs(friction))
				setVelocity(velocity - friction);
			else
				setVelocity(0);
		}

		if (maximumVelocity > 0) {
//			setMaximumVelocity(maximumVelocity * velocityX / velocity, maximumVelocity * velocityY / velocity);
			setMaximumVelocity(maximumVelocity);
			if (Math.abs(velocity) > Math.abs(maximumVelocity))
				setVelocity(maximumVelocityX);
//			if (Math.abs(velocityX) > Math.abs(maximumVelocityX))
//				velocityX = maximumVelocityX;
//			if (Math.abs(velocityY) > Math.abs(maximumVelocityY))
//				velocityY = maximumVelocityY;
		}

		setLocation(locationX + velocityX, locationY + velocityY);

//		locationX += velocityX;
//		locationY += velocityY;
	}

	public final void draw() {
		if (isVisible) {
			gameEngine.getGraphicsContext().save();
			drawObject();
			gameEngine.getGraphicsContext().restore();
		}
	}

	protected abstract void drawObject();
	public abstract Shape getHitBox();
	public static void setGameEngine(GameEngine gameEngine) { GameObject.gameEngine = gameEngine; }
	public static GameEngine getGameEngine() { return GameObject.gameEngine; }
	public static GraphicsContext getGraphicsContext() { return gameEngine.getGraphicsContext(); }

	public String getObjectName() { return objectName; }
	public void setObjectName(String objectName) { this.objectName = objectName; }
	public String getObjectType() { return objectType; }
	public void setObjectType(String objectType) { this.objectType = objectType; }

	public double getRotation() { return rotation; }
	public void setRotation(double rotation) { this.rotation = rotation % 360; }
	public double getRotationSpeed() { return rotationSpeed; }
	public void setRotationSpeed(double rotationSpeed) { this.rotationSpeed = rotationSpeed; }

	public double getAccelerationX() { return accelerationX; }
	public void setAccelerationX(double accelerationX) { 
		this.accelerationX = accelerationX; 
		this.acceleration = Math.hypot(accelerationX, this.accelerationY);
	}
	public double getAccelerationY() { return accelerationY; }
	public void setAccelerationY(double accelerationY) { 
		this.accelerationY = accelerationY;
		this.acceleration = Math.hypot(this.accelerationX, accelerationY);
	}
	public double getAcceleration() { return acceleration; }
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
		this.accelerationX = acceleration * Math.sin(rotation);
		this.accelerationY = acceleration * Math.cos(rotation);
	}
	public void setAcceleration(double accelerationX, double accelerationY) {
		this.acceleration = Math.hypot(accelerationX, accelerationY);
		this.accelerationX = accelerationX;
		this.accelerationY = accelerationY;
	}

	public double getFrictionX() { return frictionX; }
	public void setFrictionX(double frictionX) {
		this.frictionX = frictionX;
		this.friction = Math.hypot(frictionX, this.frictionY);
	}
	public double getFrictionY() { return frictionY; }
	public void setFrictionY(double frictionY) {
		this.frictionY = frictionY;
		this.friction = Math.hypot(this.frictionX, frictionY);
	}
	public double getFriction() { return friction; }
	public void setFriction(double friction) {
		this.friction = friction;
		this.frictionX = -friction * velocityX / velocity;
		this.frictionY = -friction * velocityY / velocity;
	}
	public void setFriction(double frictionX, double frictionY) {
		this.friction = Math.hypot(frictionX, frictionY);
		this.frictionX = frictionX;
		this.frictionY = frictionY;
	}
	
	public double getVelocityX() { return velocityX; }
	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
		this.velocity = Math.hypot(velocityX, this.velocityY);
	}
	public double getVelocityY() { return velocityY; }
	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
		this.velocity = Math.hypot(this.velocityX, velocityY);
	}
	public double getVelocity() { return velocity; }
	public void setVelocity(double velocity) {
		if (velocity == 0)
			this.velocityX = this.velocityY = this.velocity = 0;
		else {
			this.velocityX = velocity * accelerationX / acceleration;
			this.velocityY = velocity * accelerationY / acceleration;
			this.velocity = velocity;
		}
//		this.velocityX = velocity * accelerationX / acceleration;
//		this.velocityY = velocity * accelerationY / acceleration;
	}
	public void setVelocity(double velocityX, double velocityY) { 
		this.velocity = Math.hypot(velocityX, velocityY);
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}

	public double getMaximumVelocityX() { return maximumVelocityX; }
	public void setMaximumVelocityX(double maximumVelocityX) {
		this.maximumVelocityX = maximumVelocityX;
		this.maximumVelocity = Math.hypot(maximumVelocityX, this.maximumVelocityY);
	}
	public double getMaximumVelocityY() { return maximumVelocityY; }
	public void setMaximumVelocityY(double maximumVelocityY) {
		this.maximumVelocityY = maximumVelocityY;
		this.maximumVelocity = Math.hypot(this.maximumVelocityX, maximumVelocityY);
	}
	public double getMaximumVelocity() { return maximumVelocity; }
	public void setMaximumVelocity(double maximumVelocity) {
		this.maximumVelocity = maximumVelocity;
		this.maximumVelocityX = maximumVelocity * velocityX / velocity;
		this.maximumVelocityY = maximumVelocity * velocityY / velocity;

	}
	public void setMaximumVelocity(double maximumVelocityX, double maximumVelocityY) { 
		this.maximumVelocity = Math.hypot(maximumVelocityX, maximumVelocityY);
		this.maximumVelocityX = maximumVelocityX;
		this.maximumVelocityY = maximumVelocityY;
	}

	public double getLocationX() { return locationX; }
	public void setLocationX(double locationX) { this.locationX = locationX; }
	public double getLocationY() { return locationY; }
	public void setLocationY(double locationY) { this.locationY = locationY; }
	public void setLocation(double locationX, double locationY) { 
		this.locationX = locationX; 
		this.locationY = locationY; 
	}

	public double getMass() { return mass; }
	public void setMass(double mass) { this.mass = mass; }

	public boolean isVisible() { return isVisible; }
	public void setVisibility(boolean visible) { isVisible = visible; }

	@Override
//	public String toString() { return String.format("[%s: %s]", objectName, objectType); }
	public String toString() { return String.format("%s %s %s %s", velocity, velocityX, velocityY, rotation); }

}