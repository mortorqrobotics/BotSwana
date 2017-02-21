package org.team1515.botswana.commands.movement;

import org.team1515.botswana.Robot;
import org.team1515.botswana.util.WheelSpeeds;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardAnglePID extends Command {
	
	static final double MIN_SPEED = 0.15;
	static final double MIN_ERROR = 0.75;

	static final double P = 0.00000001;
	static final double I = 0;
	static final double D = 0;

	double gyroStartAngle;
	double errorSum;
	double lastError = 0;
	double speed;
	
    public DriveForwardAnglePID(double speed) {
    	requires(Robot.driveTrain);
    	this.speed = speed;
    }
    
	@Override
    protected void initialize() {
//		Robot.driveTrain.moveForward(speed);
		gyroStartAngle = Robot.gyro.getAngle();
	}

	@Override
	protected void execute() {
		double error = -(Robot.gyro.getAngle() - gyroStartAngle);
		double speed = error * P + errorSum * I + (error - lastError) * D;
		errorSum += error;
		if (Math.abs(speed) < MIN_SPEED) {
			speed = Math.signum(speed) * MIN_SPEED;
		}
 		Robot.driveTrain.setSpeed(new WheelSpeeds(speed + this.speed, -speed + this.speed, speed + this.speed, -speed + this.speed));
		lastError = error;
	}

	@Override
	protected boolean isFinished() {
		return !Robot.limitSwitch.get();
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
