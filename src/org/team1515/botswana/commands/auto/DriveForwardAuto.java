package org.team1515.botswana.commands.auto;

import org.team1515.botswana.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardAuto extends Command {
	
	double speed;
	double distance;
	
	double distanceSum;
	int lastEncoderPosition;
	
	public DriveForwardAuto(double speed) {
		requires(Robot.driveTrain);
		this.speed = speed;
	}
	
	public DriveForwardAuto(double speed, double distance) {
		this(speed);
		this.distance = distance;
	}

	protected boolean isFinished() {
		Robot.driveTrain.moveForwardDistance(distance);
		Robot.driveTrain.resetEncoders();
		return Robot.driveTrain.onDistanceTarget(0.5);
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