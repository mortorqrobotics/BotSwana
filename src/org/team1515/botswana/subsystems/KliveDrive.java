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
		z *= getThrottle();
		
		return new Triple<Double>(x, y, z);
	}

	protected double getThrottle() {
		return (-joystick.getRawAxis(Controls.AXIS_THROTTLE) + 1) / 2;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoystickDrive());	
	}
}
