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

		x *= getThrottle();
		y *= getThrottle();
		z *= Math.sqrt(getThrottle()) * 0.5;
		
//		if (x != 0) x = 0.2 * Math.signum(x);
//		if (y != 0) y = 0.2 * Math.signum(y);
//		
		if (!Robot.driveStick.getRawButton(6)) {
			x = 0;
		}

		return new Triple<Double>(x, y, z);
	}

	protected double getThrottle() {
//		if (true) return 0.6;
		return (-Robot.driveStick.getRawAxis(Controls.AXIS_THROTTLE) + 1) / 2;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoystickDrive());	
	}
}
