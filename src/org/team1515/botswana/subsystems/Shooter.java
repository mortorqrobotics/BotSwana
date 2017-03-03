package org.team1515.botswana.subsystems;

import org.team1515.botswana.RobotMap;
import org.team1515.botswana.util.MotorModule;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	MotorModule frontMotors;
	MotorModule backMotors;

	private static final double SPEED_INTAKE = 0.7;
//	private static final double SPEED_INTAKE = 1;
	private static final double SPEED_SHOOT = 1;

	public Shooter() {
		frontMotors = new MotorModule(RobotMap.MOTORS_FRONT_SHOOTER);
		backMotors = new MotorModule(RobotMap.MOTORS_BACK_SHOOTER);
	}

	public void shoot() {
		frontMotors.setSpeed(1);
		backMotors.setSpeed(SPEED_SHOOT);
	}

	public void intake() {
		frontMotors.setSpeed(0.5);
		backMotors.setSpeed(1);
	}
	
	public void reverseShoot() {
		frontMotors.setSpeed(-SPEED_SHOOT);
		backMotors.setSpeed(-SPEED_SHOOT);
	}
	
	public void reverseIntake() {
		frontMotors.setSpeed(-SPEED_INTAKE);
		backMotors.setSpeed(-SPEED_INTAKE);
	}

	public void stop() {
		frontMotors.stop();
		backMotors.stop();
	}

	@Override
	protected void initDefaultCommand() {

	}

}
