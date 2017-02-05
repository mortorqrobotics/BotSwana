package org.usfirst.frc.team1515.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team1515.robot.Robot;
import org.usfirst.frc.team1515.robot.util.Triple;
import org.usfirst.frc.team1515.robot.util.WheelSpeeds;

import java.util.Collections;

public abstract class MecanumDrive extends Subsystem {

	protected static final double DEAD_BAND = 0.15;
	protected static final double DRIVING_SCALE = 1.0;
	protected static final double TURNING_SCALE = 1.0;

	static double a = 0.5;
	static double b = 1.0;
	static double c = 0.3;
	static double d = 0.3;

	protected static final WheelSpeeds corner000 = new WheelSpeeds(0, 0, 0, 0);
	protected static final WheelSpeeds corner001 = new WheelSpeeds(b, -b, b, -b);
	protected static final WheelSpeeds corner010 = new WheelSpeeds(1, 1, 1, 1);
	protected static final WheelSpeeds corner011 = new WheelSpeeds(1, a, 1, a);
	protected static final WheelSpeeds corner100 = new WheelSpeeds(1, -1, -1, 1);
	protected static final WheelSpeeds corner101 = new WheelSpeeds(1, 1, -a, a);
	protected static final WheelSpeeds corner110 = new WheelSpeeds(1, 0, 0, 1);
	protected static final WheelSpeeds corner111 = new WheelSpeeds(1, -c, c, d);

	protected MecanumWheel topLeftWheel;
	protected MecanumWheel topRightWheel;
	protected MecanumWheel bottomLeftWheel;
	protected MecanumWheel bottomRightWheel;

	protected List<MecanumWheel> wheels;

	protected Joystick joystick;

	public MecanumDrive(Joystick joystick) {
		topLeftWheel = new MecanumWheel(22, 2);
		topRightWheel = new MecanumWheel(24, 0);
		bottomLeftWheel = new MecanumWheel(21, 1);
		bottomRightWheel = new MecanumWheel(23, 3);

		wheels = new ArrayList<MecanumWheel>();
		wheels.add(topLeftWheel);
		wheels.add(topRightWheel);
		wheels.add(bottomLeftWheel);
		wheels.add(bottomRightWheel);
		this.wheels = Collections.unmodifiableList(wheels);

		this.joystick = joystick;
	}

	private double bound(double speed) {
		if (speed > 1.0) {
			return 1.0;
		} else if (speed < -1.0) {
			return -1.0;
		} else {
			return speed;
		}
	}

	public void setSpeed(WheelSpeeds speeds) {
		double factor = 1; // set to -1 to reverse wheels
		topLeftWheel.setSpeed(-bound(speeds.topLeft) * factor);
		topRightWheel.setSpeed(bound(speeds.topRight) * factor);
		bottomLeftWheel.setSpeed(-bound(speeds.bottomLeft) * factor);
		bottomRightWheel.setSpeed(bound(speeds.bottomRight) * factor);
	}

	public void moveForward(double speed) {
		setSpeed(new WheelSpeeds(speed, speed, speed, speed));
	}

	public void moveBackward(double speed) {
		setSpeed(new WheelSpeeds(-speed, -speed, -speed, -speed));
	}

	public void moveLeft(double speed) {
		setSpeed(new WheelSpeeds(-speed, speed, speed, -speed));
	}

	public void moveRight(double speed) {
		setSpeed(new WheelSpeeds(speed, -speed, -speed, speed));
	}

	public void rotateLeft(double speed) {
		setSpeed(new WheelSpeeds(-speed, speed, -speed, speed));
	}

	public void rotateRight(double speed) {
		setSpeed(new WheelSpeeds(speed, -speed, speed, -speed));
	}

	public void stop() {
		setSpeed(new WheelSpeeds(0, 0, 0, 0));
	}

	// public void resetEncoders() {
	// for(MecanumWheel wheel : wheels) {
	// wheel.encoder.reset();
	// }
	// }

	public Triple<Double> getRelativeJoystick(Triple<Double> joystickValues) {
		double x = joystickValues.first;
		double y = joystickValues.second;
		double gyroAngle = Robot.gyro.getAngle();
		System.out.println(gyroAngle);
//		gyroAngle = 90 - gyroAngle;
		// gyroAngle = (gyroAngle % 360 + 360) % 360; // normalize gyro angle
		gyroAngle *= Math.PI / 180;

		double angle = x == 0 ? (Math.signum(y) * Math.PI / 2) : Math.atan(y / x);
		if (x < 0) {
			angle += Math.PI;
		}
		double power = Math.max(Math.abs(x), Math.abs(y));
		double newAngle = angle + gyroAngle;
		double sin = Math.sin(newAngle);
		double cos = Math.cos(newAngle);
		double resultX, resultY;
		if (Math.abs(sin) > Math.abs(cos)) {
			resultY = power * Math.signum(sin);
			resultX = power * cos / sin * Math.signum(cos);
		} else {
			resultX = power * Math.signum(cos);
			resultY = power * sin / cos * Math.signum(sin);
		}

		return new Triple<Double>(resultX, resultY, joystickValues.third);
		// return joystickValues;
	}

	protected abstract Triple<Double> getJoystickXYZ();

	public void setXYZ(double x, double y, double z) {
		x *= -DRIVING_SCALE;
		y *= DRIVING_SCALE;
		z *= TURNING_SCALE;
		// setSpeed(y + x + z, y - x - z, y + x - z, y - x + z); //is this
		// right? no
		WheelSpeeds line000To100 = WheelSpeeds.add(corner000,
				WheelSpeeds.subtract(corner100, corner000).multiply(Math.abs(x)));
		WheelSpeeds line010To110 = WheelSpeeds.add(corner010,
				WheelSpeeds.subtract(corner110, corner010).multiply(Math.abs(x)));
		WheelSpeeds planeZ0 = WheelSpeeds.add(line000To100,
				WheelSpeeds.subtract(line010To110, line000To100).multiply(Math.abs(y)));
		WheelSpeeds line001To101 = WheelSpeeds.add(corner001,
				WheelSpeeds.subtract(corner101, corner001).multiply(Math.abs(x)));
		WheelSpeeds line011To111 = WheelSpeeds.add(corner011,
				WheelSpeeds.subtract(corner111, corner011).multiply(Math.abs(x)));
		WheelSpeeds planeZ1 = WheelSpeeds.add(line001To101,
				WheelSpeeds.subtract(line011To111, line001To101).multiply(Math.abs(y)));
		WheelSpeeds cube = WheelSpeeds.add(planeZ0, WheelSpeeds.subtract(planeZ1, planeZ0).multiply(Math.abs(z)));

		if (x < 0) {
			cube = new WheelSpeeds(cube.bottomLeft, cube.bottomRight, cube.topLeft, cube.topRight);
		}
		if (y < 0) {
			cube = new WheelSpeeds(-cube.topRight, -cube.topLeft, -cube.bottomRight, -cube.bottomLeft);
		}
		if (z < 0) {
			cube = new WheelSpeeds(cube.bottomRight, cube.bottomLeft, cube.topRight, cube.topLeft);
		}

		System.out.print(cube.topLeft + "\t");
		System.out.print(cube.topRight + "\t");
		System.out.print(cube.bottomLeft + "\t");
		System.out.println(cube.bottomRight);

		setSpeed(cube);
	}

	public void drive() {
		// topRightWheel.encoder.update();
		Triple<Double> triple = getJoystickXYZ();
//		triple = getRelativeJoystick(triple);
		setXYZ(triple.first, triple.second, triple.third);
//		 topLeftWheel.setSpeed(0.3);
//		 topRightWheel.setSpeed(0.3);
//		 bottomLeftWheel.setSpeed(0.3);
//		 bottomRightWheel.setSpeed(0.3);
//		 topLeftWheel.encoder.update();
		// topRightWheel.encoder.update();
		// bottomLeftWheel.encoder.update();
		// bottomRightWheel.encoder.update();
		// System.out.println(topLeftWheel.encoder.getSpeed());
		// System.out.println(topLeftWheel.encoder.getPosition());
		// System.out.println(topLeftWheel.encoder.getSpeed() + "\t" +
		// topRightWheel.encoder.getSpeed() + "\t" +
		// bottomLeftWheel.encoder.getSpeed() + "\t" +
		// bottomRightWheel.encoder.getSpeed());

		// SmartDashboard.putNumber("encoderVal",
		// topRightWheel.encoder.getSpeed());
		a = SmartDashboard.getNumber("a", 0.0);
		b = SmartDashboard.getNumber("b", 1.0);
		c = SmartDashboard.getNumber("c", 1.0);
		d = SmartDashboard.getNumber("d", 1.0);
	}
}
