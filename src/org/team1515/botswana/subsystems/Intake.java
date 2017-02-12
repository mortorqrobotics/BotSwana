package org.team1515.botswana.subsystems;

import org.team1515.botswana.RobotMap;
import org.team1515.botswana.util.MotorModule;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	
	MotorModule motors;
	
	private static final double SPEED = 1;
	
	public Intake() {
		motors = new MotorModule(RobotMap.MOTORS_INTAKE);
	}
	
	public void start() {
		motors.setSpeed(SPEED);
	}
	
	public void stop() {
		motors.stop();
	}

	@Override
	protected void initDefaultCommand() {

	}

}
