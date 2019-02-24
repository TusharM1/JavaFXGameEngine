package GameEngine.Utilities;

public class Vector2D {

	//---------------------------- Declare Fields ----------------------------//

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
	// TODO check if offset should be added to parameter list
	public static Vector2D createCartesianVector(double componentX, double componentY) {
		Vector2D vector2D = new Vector2D();
		vector2D.setCartesianCoordinates(componentX, componentY, 0);
		return vector2D;
	}

	// Generates a Polar Vector
	public static Vector2D createPolarVector(double magnitude, double angle) {
		Vector2D vector2D = new Vector2D();
		vector2D.setPolarCoordinates(magnitude, angle);
		return vector2D;
	}

	//---------------------------- Set Coordinates ----------------------------//

//	// Sets Cartesian Coordinates directly; Overrides all fields
//	public void setCartesianCoordinates(double componentX, double componentY) {
//		this.componentX = componentX;
//		this.componentY = componentY;
//		calculatePolarCoordinates();
//	}

	// Sets Cartesian Coordinates with an initial offset angle
	public void setCartesianCoordinates(double componentX, double componentY, double offsetAngle) {

//		System.out.print(componentX + " " + -componentY + " " + offsetAngle + " ");

		setPolarCoordinates(Math.hypot(componentX, componentY), Math.toDegrees(Math.atan2(componentY, componentX)) + offsetAngle);

//		this.magnitude = Math.hypot(componentX, componentY);
//
//		setAngle(Math.toDegrees(Math.atan2(componentY, componentX)) + offsetAngle);
//
//		this.angleDegrees = ((90 + Math.toDegrees(Math.atan2(componentY, componentX)) + offsetAngle) + 360) % 360;
//		this.angleRadians = Math.toRadians(this.angleDegrees);
//
//		this.componentX = this.magnitude * Math.sin(this.angleRadians);
//		this.componentY = this.magnitude * -Math.cos(this.angleRadians);

		//		this.angleDegrees = (90 + Math.toDegrees(Math.atan2(componentY, componentX) + offsetAngle) + 360) % 360;
//		this.angleRadians = Math.toRadians(this.angleDegrees);

//		calculateCartesianCoordinates();

//		this.componentX = this.magnitude * Math.sin(this.angleRadians);
//		this.componentY = -this.magnitude * Math.cos(this.angleRadians);

//		System.out.println(this.componentX + " " + this.componentY + " " + this.angleDegrees);
//		System.out.println(this.magnitude + " " + this.componentX + " " + this.componentY + " " + this.angleDegrees);

	}

	// Sets Polar Coordinates directly; Overrides all fields
	public void setPolarCoordinates(double magnitude, double angle) {
		this.magnitude = magnitude;
		this.angleDegrees = ((90 + angle) + 360) % 360;
		this.angleRadians = Math.toRadians(this.angleDegrees);
		calculateCartesianCoordinates();
	}

	//---------------------------- Calculate Vector Components ----------------------------//

	// Updates Magnitude and Angle given that X and Y Components are Up-To-Date
	private void calculatePolarCoordinates() {
		this.magnitude = Math.hypot(this.componentX, this.componentY);
		this.angleRadians = Math.atan2(this.componentX, this.componentY);
		this.angleDegrees = Math.toDegrees(this.angleRadians);
	}

	// Updates X and Y Components given that Magnitude and Angle are Up-To-Date
	// Check the Negative Sign On This function
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
		this.angleDegrees = ((90 + angle) + 360) % 360;
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

		/*

    var x1 = r * 1 * cos(theta);
    var y1 = r * -1 * sin(theta);
    // var x1 = 120;
    // var y1 = 120 * -1;

    var x2 = (x1*cos(offset) - y1*sin(offset));
    var y2 = (x1*sin(offset) + y1*cos(offset));
    var theta2 = (atan(y2/x2) + 360) % 360;

		 */

//		double magnitude = Math.hypot(componentX, componentY);
//		double angle = Math.toDegrees(Math.atan2(componentY, componentX));
//
//		double x1 = magnitude * Math.cos(angle);
//		double y1 = -magnitude * Math.sin(angle);
//
//		double x2 = (x1 * Math.cos(offsetAngle) - y1 * Math.sin(offsetAngle));
//		double y2 = (x1 * Math.sin(offsetAngle) + y1 * Math.cos(offsetAngle));
//
//		double angle2 = (Math.toDegrees(Math.atan2(y2, x2)) + 360) % 360;
//
//		this.magnitude = magnitude;
//		this.angleRadians = Math.atan2(y2, x2);
//		this.angleDegrees = Math.toDegrees(angleRadians);
//		this.componentX = x2;
//		this.componentY = y2;

//		double vectorAngle = ;
//		double finalAngle = offsetAngle + vectorAngle;

//		this.angleDegrees = (90 - Math.toDegrees(Math.atan2(componentY, componentX)) + offsetAngle + 360) % 360;
//		this.angleRadians = Math.toRadians(this.angleDegrees);
//
//		calculateCartesianCoordinates();
//		System.out.println(this);