package org.team1515.botswana.subsystems;

import org.team1515.botswana.RobotMap;
import org.team1515.botswana.util.MotorModule;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	
	MotorModule frontMotors;
	MotorModule backMotors;
	
	private static final double SPEED = 1;
	
	public Shooter() {
		frontMotors = new MotorModule(RobotMap.MOTORS_FRONT_SHOOTER);
		backMotors = new MotorModule(RobotMap.MOTORS_BACK_SHOOTER);
	}
	
	public void shoot() {
		frontMotors.setSpeed(SPEED);
		backMotors.setSpeed(SPEED);
	}
	
	public void intake() {
		frontMotors.setSpeed(SPEED);
		backMotors.setSpeed(-SPEED);
	}

	public void stop() {
		frontMotors.stop();
		backMotors.stop();
	}

	@Override
	protected void initDefaultCommand() {
		
	}

}
