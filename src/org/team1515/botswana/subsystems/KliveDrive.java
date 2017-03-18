package org.team1515.botswana.subsystems;

import org.team1515.botswana.Controls;
import org.team1515.botswana.Robot;
import org.team1515.botswana.commands.JoystickDrive;
import org.team1515.botswana.util.Triple;

public class KliveDrive extends MecanumDrive {

	protected Triple<Double> getJoystickXYZ() {
		double x = Robot.driveStick.getRawAxis(Controls.AXIS_SIDE);
		double y = -Robot.driveStick.getRawAxis(Controls.AXIS_FORWARD);
		double z = Robot.driveStick.getRawAxis(Controls.AXIS_TWIST);

		if(Math.abs(x) <= DEAD_BAND) {
			x = 0.0;
		}
		if(Math.abs(y) <= DEAD_BAND) {
			y = 0.0;
		}
		if(Math.abs(z) <= DEAD_BAND) {
			z = 0.0;
		}
		
		if (getThrottle() > 0.5) {
			if (!Robot.driveStick.getRawButton(8)) {
				z *= 0.4;
			}
		} else {
//			x *= 0.2;
			y *= 0.2;
			if (!Robot.driveStick.getRawButton(8)) {
				z *= 0.4;
			}
		}

//		x *= getThrottle();
//		y *= getThrottle();
//		z *= Math.sqrt(getThrottle()) * 0.5;
		
//		if (x != 0) x = 0.2 * Math.signum(x);
//		if (y != 0) y = 0.2 * Math.signum(y);
//		
		if (!Robot.driveStick.getRawButton(6)) {
			x = 0;
		}

		return new Triple<Double>(x, y, z);
	}

	protected double getThrottle() {
		double throttle = (-Robot.driveStick.getRawAxis(Controls.AXIS_THROTTLE) + 1) / 2;
//		if (throttle > 0.5) {
//			// normal speed
//			return 0.8;
//		} else {
//			// small adjustment speed
//			return 0.3;
//		}
		return throttle;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoystickDrive());	
	}
}
