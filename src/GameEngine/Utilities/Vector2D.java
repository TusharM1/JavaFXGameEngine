package GameEngine.Utilities;

public class Vector2D {

	//---------------------------- Declare Fields ----------------------------//

	// Cartesian Coordinate System
	private double componentX, componentY;

	// Polar Coordinate System
	private double magnitude, angle;

	//---------------------------- Create Vector ----------------------------//

	// Vector2D Constructor: Initializes all fields
	public Vector2D() {
		this.componentX = 0;
		this.componentY = 0;
		this.magnitude = 0;
		this.angle = 0;
	}

	// Generates a Cartesian Vector
	public static Vector2D createCartesianVector(double componentX, double componentY) {
		Vector2D vector2D = new Vector2D();
		vector2D.setCartesianCoordinates(componentX, componentY);
		return vector2D;
	}

	// Generates a Polar Vector
	public static Vector2D createPolarVector(double magnitude, double angle) {
		Vector2D vector2D = new Vector2D();
		vector2D.setPolarCoordinates(magnitude, angle);
		return vector2D;
	}

	//---------------------------- Set Vector ----------------------------//

	// Sets Cartesian Coordinates directly; Overrides all fields
	public void setCartesianCoordinates(double componentX, double componentY) {
		this.componentX = componentX;
		this.componentY = componentY;
		calculatePolarCoordinates();
	}

	// Sets Polar Coordinates directly; Overrides all fields
	public void setPolarCoordinates(double magnitude, double angle) {
		this.magnitude = magnitude;
		this.angle = angle;
		calculateCartesianCoordinates();
	}

	//---------------------------- Calculate Vector Components ----------------------------//

	// Updates Magnitude and Angle given that X and Y Components are Up-To-Date
	private void calculatePolarCoordinates() {
		this.magnitude = Math.hypot(this.componentX, this.componentY);
		this.angle = Math.atan2(this.componentY, this.componentX);
	}

	// Updates X and Y Components given that Magnitude and Angle are Up-To-Date
	private void calculateCartesianCoordinates() {
		this.componentX = magnitude * Math.sin(this.angle);
		this.componentY = magnitude * Math.cos(this.angle);
	}

	//---------------------------- Vector Addition ----------------------------//

	// Add another vector to this vector
	public void add(Vector2D vector2D) {
		this.componentX += vector2D.getComponentX();
		this.componentY += vector2D.getComponentY();
		calculatePolarCoordinates();
	}

	// Add Cartesian vector components to this Vector
	public void addCartesianVector(double componentX, double componentY) {
		this.componentX += componentX;
		this.componentY += componentY;
		calculatePolarCoordinates();
	}

	// Add Polar vector components to this vector
	public void addPolarVector(double magnitude, double angle) {
		this.componentX += magnitude * Math.sin(angle);
		this.componentY += magnitude * Math.cos(angle);
		calculateCartesianCoordinates();
	}

	public static Vector2D add(Vector2D vector1, Vector2D vector2) {
		return Vector2D.createCartesianVector(vector1.getComponentX() + vector2.getComponentX(),
				vector2.getComponentY() + vector2.getComponentY());
	}

	//---------------------------- Vector Subtraction ----------------------------//

	public void subtract(double componentX, double componentY) {
		this.componentX -= componentX;
		this.componentY -= componentY;
		calculatePolarCoordinates();
	}

	//---------------------------- Vector Scaling ----------------------------//

	public void scale(double scalar) {
		this.magnitude *= scalar;
		calculateCartesianCoordinates();
	}

	//---------------------------- Setters ----------------------------//

	// Setters for Fields; Modifies all but the complementary field
	public void setComponentX(double componentX) {
		this.componentX = componentX;
		calculatePolarCoordinates();
	}

	public void setComponentY(double componentY) {
		this.componentY = componentY;
		calculatePolarCoordinates();
	}

	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
		calculateCartesianCoordinates();
	}

	public void setAngle(double angle) {
		this.angle = angle;
		calculateCartesianCoordinates();
	}

	//---------------------------- Getters ----------------------------//

	// Getters for Fields
	public double getComponentX() { return componentX; }
	public double getComponentY() { return componentY; }
	public double getMagnitude() { return magnitude; }
	public double getAngle() { return angle; }


	//---------------------------- To String ----------------------------//

	@Override
	public String toString() {
		return String.format("ComponentX: %f, ComponentY: %f, Magnitude: %f, Angle: %f",
				this.componentX, this.componentY, this.magnitude, this.angle);
	}


	//	// TODO Rework this to be less stupid
//	public Vector2D(double argA, double argB, CoordinateType coordinateType) {
//		if (coordinateType == CoordinateType.CARTESIAN) {
//			this.componentX = argA;
//			this.componentY = argB;
//			this.magnitude = Math.hypot(argA, argB);
//			this.angle = Math.atan2(argB, argA);
//		}
//		else if (coordinateType == CoordinateType.POLAR) {
//			this.magnitude = argA;
//			this.angle = argB;
//			this.componentX = argA * Math.sin(argB);
//			this.componentY = argA * Math.cos(argB);
//		}
//	}

	//	public enum CoordinateType {
//		CARTESIAN, POLAR
//	}
	
}
