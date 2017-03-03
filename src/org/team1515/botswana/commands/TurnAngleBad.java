package org.team1515.botswana.commands;

import org.team1515.botswana.Robot;
import org.team1515.botswana.util.WheelSpeeds;

import edu.wpi.first.wpilibj.command.Command;

public class TurnAngleBad extends Command {

	static final double SPEED = 0.15;

	double targetAngle;
	double gyroStartAngle;

	public TurnAngleBad(double targetAngle) {
		requires(Robot.driveTrain);
		this.targetAngle = targetAngle;
	}

	@Override
	protected void initialize() {
		gyroStartAngle = Robot.gyro.getAngle();
		int sign = targetAngle < 0 ? 1 : -1;
		double speed = SPEED * sign;
		Robot.driveTrain.setSpeed(new WheelSpeeds(-speed, speed, -speed, speed));
	}

	@Override
	protected boolean isFinished() {
		if (targetAngle < 0) {
			return Robot.gyro.getAngle() < gyroStartAngle + targetAngle;
		} else {
			return Robot.gyro.getAngle() > gyroStartAngle + targetAngle;
		}
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
