package Tests;

import GameEngine.Utilities.Vector2D;

import java.util.Scanner;

public class VectorTest {

	Scanner scanner;
	Vector2D vector2D;

	double x, y, a;

	public static void main(String[] args) {
		new VectorTest();
	}

	VectorTest() {
		scanner = new Scanner(System.in);
		vector2D = new Vector2D();

		while (true)
			input();
	}

	void input() {
		double a = scanner.nextDouble();
		double b = scanner.nextDouble();
//		double c = scanner.nextDouble();

//		vector2D.setCartesianCoordinates(a, b, c);
//		x += vector2D.getComponentX();
//		y += vector2D.getComponentY();
//		System.out.println(String.format("%s | %f %f %f", vector2D, x, y, c));

		vector2D.setPolarCoordinates(a, b);
		double x = vector2D.getMagnitude();
		double y = vector2D.getAngle();
		System.out.println(String.format("%s | %f %f", vector2D, x, y));
	}

}
