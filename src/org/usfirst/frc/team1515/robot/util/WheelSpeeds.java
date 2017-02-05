package org.usfirst.frc.team1515.robot.util;

public class WheelSpeeds {

	public double topLeft;
	public double topRight;
	public double bottomLeft;
	public double bottomRight;

	public WheelSpeeds(double topLeft, double topRight, double bottomLeft, double bottomRight) {
		this.topLeft = topLeft;
		this.topRight = topRight;
		this.bottomLeft = bottomLeft;
		this.bottomRight = bottomRight;
	}

	public WheelSpeeds multiply(double num) {
		return new WheelSpeeds(this.topLeft * num, this.topRight * num, this.bottomLeft * num, this.bottomRight * num);
	}

	public static WheelSpeeds add(WheelSpeeds speeds1, WheelSpeeds speeds2) {
		return new WheelSpeeds(
			speeds1.topLeft + speeds2.topLeft,
			speeds1.topRight + speeds2.topRight,
			speeds1.bottomLeft + speeds2.bottomLeft,
			speeds1.bottomRight + speeds2.bottomRight
		);
	}

	public static WheelSpeeds subtract(WheelSpeeds subtractee, WheelSpeeds subtracter) {
		return new WheelSpeeds(
			subtractee.topLeft - subtracter.topLeft,
			subtractee.topRight - subtracter.topRight,
			subtractee.bottomLeft - subtracter.bottomLeft,
			subtractee.bottomRight - subtracter.bottomRight
		);
	}
}
