package org.team1515.botswana.subsystems;

import org.team1515.botswana.RobotMap;
import org.team1515.botswana.util.MotorModule;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	MotorModule intakeMotors;
	MotorModule shooterMotors;

	private static final double SPEED = 1;

	public Shooter() {
		intakeMotors = new MotorModule(RobotMap.MOTORS_INTAKE);
		shooterMotors = new MotorModule(RobotMap.MOTORS_SHOOTER);
	}

	public void shoot() {
		intakeMotors.setSpeed(SPEED);
		shooterMotors.setSpeed(-SPEED);
	}

	public void intake() {
		intakeMotors.setSpeed(SPEED);
		shooterMotors.setSpeed(SPEED);
	}

	public void stop() {
		intakeMotors.stop();
		shooterMotors.stop();
	}

	@Override
	protected void initDefaultCommand() {

	}

}
