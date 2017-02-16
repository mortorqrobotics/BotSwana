package org.team1515.botswana.commands;

import org.team1515.botswana.Robot;
import org.team1515.botswana.util.WheelSpeeds;

import edu.wpi.first.wpilibj.command.Command;

public class TurnAngle extends Command {
	
	static final double MIN_SPEED = 0.15;
	static final double MIN_ERROR = 0.75;
	static final int ERROR_INCREMENT_FINISH = 2;
	
	static final double P = 0.00000001;
	static final double I = 0;
	static final double D = 0;
	
	double targetAngle;
	double gyroStartAngle;
	
	double errorSum;
	double lastError = 0;
	int errorIncrement = 0;

    public TurnAngle(double targetAngle) {
    	requires(Robot.driveTrain);
    	this.targetAngle = targetAngle;
    }

    @Override
    protected void initialize() {
    	errorIncrement = 0;
    }
    
    @Override
    protected boolean isFinished() {
		double error = targetAngle - (Robot.gyro.getAngle() - gyroStartAngle);
		double speed = error * P + errorSum * I + (error - lastError) * D;
		errorSum += error;
		if (Math.abs(speed) < MIN_SPEED) {
			speed = Math.signum(speed) * MIN_SPEED;
		}
		Robot.driveTrain.setSpeed(new WheelSpeeds(speed, -speed, speed, -speed));
		if (Math.abs(error) <= MIN_ERROR && lastError > MIN_ERROR) {
			errorIncrement++;
		}
		lastError = error;
		if (errorIncrement >= ERROR_INCREMENT_FINISH) {
			return true;
		}
		return false;
    }

    @Override
    protected void end() {
    	Robot.driveTrain.stop();
    }

    @Override
    protected void interrupted() {
    	end();
    }
}
