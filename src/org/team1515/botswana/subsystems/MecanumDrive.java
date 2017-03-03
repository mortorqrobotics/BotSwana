package org.team1515.botswana.subsystems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.team1515.botswana.Robot;
import org.team1515.botswana.RobotMap;
import org.team1515.botswana.util.Triple;
import org.team1515.botswana.util.WheelSpeeds;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

	public MecanumWheel topLeftWheel;
	public MecanumWheel topRightWheel;
	public MecanumWheel bottomLeftWheel;
	public MecanumWheel bottomRightWheel;
	
	boolean isReversed;

	protected List<MecanumWheel> wheels;

	public MecanumDrive() {
		topLeftWheel = new MecanumWheel(RobotMap.MOTORS_WHEEL_TOP_LEFT, RobotMap.ENCODER_WHEEL_TOP_LEFT);
		topRightWheel = new MecanumWheel(RobotMap.MOTORS_WHEEL_TOP_RIGHT, RobotMap.ENCODER_WHEEL_TOP_RIGHT);
		bottomLeftWheel = new MecanumWheel(RobotMap.MOTORS_WHEEL_BOTTOM_LEFT, RobotMap.ENCODER_WHEEL_BOTTOM_LEFT);
		bottomRightWheel = new MecanumWheel(RobotMap.MOTORS_WHEEL_BOTTOM_RIGHT, RobotMap.ENCODER_WHEEL_BOTTOM_RIGHT);

		wheels = new ArrayList<MecanumWheel>();
		wheels.add(topLeftWheel);
		wheels.add(topRightWheel);
		wheels.add(bottomLeftWheel);
		wheels.add(bottomRightWheel);
		this.wheels = Collections.unmodifiableList(wheels);
		
		isReversed = false;

	}
	
	public boolean isReversed() {
		return isReversed;
	}
	
	public void resetPID() {
		for (MecanumWheel wheel : wheels) {
			wheel.resetPID();
		}
	}
	
	public int getEncoderSum() {
		return topLeftWheel.getEncoderDistance()
				- topRightWheel.getEncoderDistance()
				+ bottomLeftWheel.getEncoderDistance()
				- bottomRightWheel.getEncoderDistance();
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
//		speeds.print();
		double factor = 1; // set to -1 to reverse wheels
		topLeftWheel.setSpeed(-bound(speeds.topLeft) * factor);
		topRightWheel.setSpeed(bound(speeds.topRight * factor));
		bottomLeftWheel.setSpeed(-bound(speeds.bottomLeft * factor));
		bottomRightWheel.setSpeed(bound(speeds.bottomRight) * factor);
	}
	
	public void initializeDistancePID(double distanceTarget) {
		for (MecanumWheel wheel : wheels) {
			wheel.itializeDistancePID(distanceTarget);
		}
	}
	
	public boolean isOnDistanceTarget() {
		for (MecanumWheel wheel : wheels) {
			if (!wheel.isOnDistanceTarget()) {
				return false;
			}
		}
		return true;
	}
	
	public void reverse() {
		isReversed = !isReversed;
	}
	
	public void undoReverse() {
		isReversed = false;
	}
	
	public WheelSpeeds getDistancePID() {
		return new WheelSpeeds(
			topLeftWheel.getDistanceError(), 
			topRightWheel.getDistanceError(), 
			bottomLeftWheel.getDistanceError(), 
			bottomRightWheel.getDistanceError()
		);
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

	public WheelSpeeds getEncoderRates() {
		return new WheelSpeeds(
				topLeftWheel.getEncoderRate(), 
				topRightWheel.getEncoderRate(),
				bottomLeftWheel.getEncoderRate(), 
				bottomRightWheel.getEncoderRate()
			);
	}
	
	public void resetEncoders() {
		for(MecanumWheel wheel : wheels) {
			wheel.encoder.reset();
		}
	}

	public Triple<Double> getRelativeJoystick(Triple<Double> joystickValues) {
		double x = joystickValues.first;
		double y = joystickValues.second;
		double gyroAngle = Robot.gyro.getAngle();
//		System.out.println(gyroAngle);
//		gyroAngle = 90 - gyroAngle;
//		gyroAngle = (gyroAngle % 360 + 360) % 360; // normalize gyro angle
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
	}

	protected abstract Triple<Double> getJoystickXYZ();

	public WheelSpeeds mecanum(double x, double y, double z) {
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
		
		return cube;
	}
	
	public void pidTest(double targetX, double targetY, double targetZ) {

		targetX *= 20000;
		targetY *= 20000;
		targetZ *= 300;

		double tlSpeed = topLeftWheel.getEncoderRate();
		double trSpeed = topRightWheel.getEncoderRate();
		double blSpeed = bottomLeftWheel.getEncoderRate();
		double brSpeed = bottomRightWheel.getEncoderRate();

		double realX = tlSpeed - trSpeed - blSpeed + brSpeed;
		double realY = tlSpeed + trSpeed + blSpeed + brSpeed;
		double realZ = Robot.gyro.getRate();

		double errorX = targetX - realX;
		double errorY = targetY - realY;
		double errorZ = targetZ - realZ;

		WheelSpeeds error = mecanum(errorX / 20000, errorY / 20000, errorZ / 300);
		
		SmartDashboard.putNumber("errorTL", error.topLeft * 5000);
		SmartDashboard.putNumber("errorTR", error.topRight * 5000);
		SmartDashboard.putNumber("errorBL", error.bottomLeft * 5000);
		SmartDashboard.putNumber("errorBR", error.bottomRight * 5000);

		topLeftWheel.setPidError(error.topLeft * 5000);
		topRightWheel.setPidError(error.topRight * 5000);
		topLeftWheel.setPidError(error.bottomLeft * 5000);
		bottomRightWheel.setPidError(error.bottomRight * 5000);
	}
	
	public static double[] trims = {2/2.8, 1, 12.0/11*2/2.8, 2/2.8};
	
	public void drive() {
		Triple<Double> triple = getJoystickXYZ();
//		triple = getRelativeJoystick(triple);
		double reverseFactor = isReversed ? -1 : 1;
		WheelSpeeds speeds = mecanum(reverseFactor * triple.first, reverseFactor * triple.second, triple.third);
//		speeds.print();
//		setSpeed(new WheelSpeeds(speeds.topLeft * 2.875 / 4, speeds.topRight * 2.875 / 3.5, speeds.bottomLeft, speeds.bottomRight * 2.875 / 3.5));
//		setSpeed(new WheelSpeeds(speeds.topLeft, speeds.topRight * 2.8/2, speeds.bottomLeft * 12.0/11, speeds.bottomRight));
		setSpeed(new WheelSpeeds(speeds.topLeft, speeds.topRight, speeds.bottomLeft, speeds.bottomRight));
//		pidTest(reverseFactor * triple.first, reverseFactor * triple.second, triple.third);
//		getEncoderRates().print();
		System.out.println(speeds.topLeft * 5000 + "\t" + topLeftWheel.getEncoderRate() + "\t" + speeds.topRight * 5000 + "\t" + topRightWheel.getEncoderRate() + "\t" +speeds.bottomLeft * 5000 + "\t" + bottomLeftWheel.getEncoderRate() + "\t" +speeds.bottomRight * 5000 + "\t" + bottomRightWheel.getEncoderRate() + "\t");
	}

}
