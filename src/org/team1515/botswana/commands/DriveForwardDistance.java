package org.team1515.botswana.commands;

import org.team1515.botswana.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardDistance extends Command {

	double distance;

	public DriveForwardDistance(double distance) {
		requires(Robot.driveTrain);
		this.distance = distance;
	}
	
	@Override
	protected void initialize() {
		Robot.driveTrain.initializeDistancePID(distance);
	}
	
	@Override
	protected void execute() {
		Robot.driveTrain.setSpeed(Robot.driveTrain.getDistancePID());
	}

	@Override
	protected boolean isFinished() {
		return Robot.driveTrain.isOnDistanceTarget();
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
