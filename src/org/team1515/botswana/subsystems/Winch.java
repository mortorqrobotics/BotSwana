package org.team1515.botswana.subsystems;

import org.team1515.botswana.RobotMap;
import org.team1515.botswana.util.MotorModule;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Winch extends Subsystem {

	MotorModule motors;

	private static final double SPEED = 1;

	public Winch() {
		motors = new MotorModule(RobotMap.MOTORS_WINCH);
	}

	public void lift() {
		motors.setSpeed(SPEED);
	}

	public void stop() {
		motors.stop();
	}

	@Override
	protected void initDefaultCommand() {

	}

}
