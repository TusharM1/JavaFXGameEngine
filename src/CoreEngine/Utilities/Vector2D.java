package CoreEngine.Utilities;

public class Vector2D {

	//---------------------------- Fields ----------------------------//

	// Cartesian Coordinate System
	private double componentX, componentY;

	// Polar Coordinate System
	private double magnitude, angleDegrees, angleRadians;

	//---------------------------- Create Vector ----------------------------//

	// Vector2D Constructor: Initializes all fields
	public Vector2D() {
		this.componentX = 0;
		this.componentY = 0;
		this.magnitude = 0;
		this.angleDegrees = 0;
		this.angleRadians = 0;
	}

	// Generates a Cartesian Vector
	public static Vector2D createCartesianVector(double componentX, double componentY, double offsetAngle) {
		Vector2D vector2D = new Vector2D();
		vector2D.setCartesianCoordinates(componentX, componentY, offsetAngle);
		return vector2D;
	}

	// Generates a Polar Vector
	public static Vector2D createPolarVector(double magnitude, double angle) {
		Vector2D vector2D = new Vector2D();
		vector2D.setPolarCoordinates(magnitude, angle);
		return vector2D;
	}

	//---------------------------- Set Coordinates ----------------------------//

	// Sets Cartesian Coordinates with an initial offset angle
	public void setCartesianCoordinates(double componentX, double componentY, double offsetAngle) {
		setPolarCoordinates(Math.hypot(componentX, componentY),
				90 + Math.toDegrees(Math.atan2(componentY, componentX)) + offsetAngle);
	}

	// Sets Polar Coordinates directly; Overrides all fields
	public void setPolarCoordinates(double magnitude, double angle) {
		this.magnitude = magnitude;
		this.angleDegrees = (angle + 360) % 360;
		this.angleRadians = Math.toRadians(this.angleDegrees);
		calculateCartesianCoordinates();
	}

	//---------------------------- Calculate Vector Components ----------------------------//

	// Updates Magnitude and Angle given that X and Y Components are Up-To-Date
	private void calculatePolarCoordinates() {
		this.magnitude = Math.hypot(this.componentX, this.componentY);
		this.angleRadians = Math.atan2(this.componentX, -this.componentY);
		this.angleDegrees = (Math.toDegrees(this.angleRadians) + 360) % 360;
	}

	// Updates X and Y Components given that Magnitude and Angle are Up-To-Date
	// Note: ComponentY is negative because pixel are drawn down in the positive direction
	private void calculateCartesianCoordinates() {
		this.componentX = this.magnitude * Math.sin(this.angleRadians);
		this.componentY = this.magnitude * -Math.cos(this.angleRadians);
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
		this.componentX += magnitude * Math.sin(Math.toRadians(angle));
		this.componentY += magnitude * Math.cos(Math.toRadians(angle));
		calculateCartesianCoordinates();
	}

	// Add two vectors together and return the resulting sum of the vector
	public static Vector2D add(Vector2D vector1, Vector2D vector2) {
		return Vector2D.createCartesianVector(vector1.getComponentX() + vector2.getComponentX(),
				vector2.getComponentY() + vector2.getComponentY(), 0);
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

	public void changeSign(double sign) {
		this.magnitude = Math.copySign(this.magnitude, sign);
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
		this.angleDegrees = (angle + 360) % 360;
		this.angleRadians = Math.toRadians(this.angleDegrees);
		calculateCartesianCoordinates();
	}

	//---------------------------- Getters ----------------------------//

	// Getters for Fields
	public double getComponentX() { return componentX; }
	public double getComponentY() { return componentY; }
	public double getMagnitude() { return magnitude; }
	public double getAngle() { return angleDegrees; }


	//---------------------------- To String ----------------------------//

	@Override
	public String toString() {
		return String.format("ComponentX: %f, ComponentY: %f, Magnitude: %f, Angle: %f",
				this.componentX, this.componentY, this.magnitude, this.angleDegrees);
	}
	
}