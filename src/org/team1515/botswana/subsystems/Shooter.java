package org.team1515.botswana.subsystems;

import org.team1515.botswana.RobotMap;
import org.team1515.botswana.util.MotorModule;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	
	MotorModule topMotors;
	MotorModule bottomFrontMotors;
	MotorModule bottomBackMotors;
	
	private static final double SPEED = 1;
	
	public Shooter() {
		topMotors = new MotorModule(RobotMap.MOTORS_TOP_SHOOTER);
		bottomFrontMotors = new MotorModule(RobotMap.MOTORS_BOTTOM_FRONT_SHOOTER);
		bottomBackMotors = new MotorModule(RobotMap.MOTORS_BOTTOM_BACK_SHOOTER);
	}
	
	public void shoot() {
		topMotors.setSpeed(SPEED);
		bottomFrontMotors.setSpeed(SPEED);
		bottomBackMotors.setSpeed(SPEED);
	}
	
	public void intake() {
		topMotors.stop();
		bottomFrontMotors.setSpeed(SPEED);
		bottomBackMotors.setSpeed(-SPEED);
	}

	public void stop() {
		topMotors.stop();
		bottomFrontMotors.stop();
		bottomBackMotors.stop();
	}

	@Override
	protected void initDefaultCommand() {
		
	}

}
