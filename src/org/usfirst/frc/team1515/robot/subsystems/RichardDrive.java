package org.usfirst.frc.team1515.robot.subsystems;

import org.usfirst.frc.team1515.robot.util.Triple;

import edu.wpi.first.wpilibj.Joystick;

public class RichardDrive extends MecanumDrive {

	public RichardDrive(Joystick joystick) {
		super(joystick);
	}

	protected Triple<Double> getJoystickXYZ() {
		double x = joystick.getRawAxis(0); //left/right
		double y = -joystick.getRawAxis(1); //forward/back
		double z = joystick.getRawAxis(5); //twist

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
		return (-joystick.getRawAxis(2) + 1) / 2;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
