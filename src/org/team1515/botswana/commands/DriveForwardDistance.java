package org.team1515.botswana.commands;

import org.team1515.botswana.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardDistance extends Command {

	double distance;

	static final double ERROR_RANGE = 0.5;

	public DriveForwardDistance(double distance) {
		requires(Robot.driveTrain);
		this.distance = distance;
	}

	@Override
	protected boolean isFinished() {
		Robot.driveTrain.moveForwardDistance(distance);
		return Robot.driveTrain.onDistanceTarget(ERROR_RANGE);
	}

	@Override
	protected void end() {
		Robot.driveTrain.resetEncoders();
		Robot.driveTrain.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
