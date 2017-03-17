package org.team1515.botswana.subsystems;

import org.team1515.botswana.RobotMap;
import org.team1515.botswana.util.MotorModule;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	MotorModule topAndBackMotors;
	MotorModule bottomFrontMotors;

	private static final double SPEED_INTAKE = 0.7;
//	private static final double SPEED_INTAKE = 1;
	private static final double SPEED_SHOOT = 1;

	public Shooter() {
		topAndBackMotors = new MotorModule(RobotMap.MOTORS_SHOOTER_TOP_AND_BACK);
		bottomFrontMotors = new MotorModule(RobotMap.MOTORS_SHOOTER_BOTTOM_FRONT);
	}

	public void shoot() {
		topAndBackMotors.setSpeed(1);
		bottomFrontMotors.setSpeed(SPEED_SHOOT);
	}

	public void intake() {
		topAndBackMotors.setSpeed(0.5);
		bottomFrontMotors.setSpeed(1);
	}
	
	public void reverseShoot() {
		topAndBackMotors.setSpeed(-SPEED_SHOOT);
		bottomFrontMotors.setSpeed(-SPEED_SHOOT);
	}
	
	public void reverseIntake() {
		topAndBackMotors.setSpeed(-SPEED_INTAKE);
		bottomFrontMotors.setSpeed(-SPEED_INTAKE);
	}

	public void stop() {
		topAndBackMotors.stop();
		bottomFrontMotors.stop();
	}

	@Override
	protected void initDefaultCommand() {

	}

}
