package org.team1515.botswana.commands.movement;

import org.team1515.botswana.Robot;
import org.team1515.botswana.util.WheelSpeeds;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardDistance extends Command {

	static final double SPEED = -0.15;
	
	int distance;
	
	int initialDistance;

	public DriveForwardDistance(int distance) {
		requires(Robot.driveTrain);
		this.distance = distance;
	}
	
	@Override
	protected void initialize() {
		initialDistance = Robot.driveTrain.getEncoderSum();
		Robot.driveTrain.setSpeed(new WheelSpeeds(SPEED, SPEED, SPEED, SPEED));
	}
	
	@Override
	protected boolean isFinished() {
		return Math.abs(initialDistance - Robot.driveTrain.getEncoderSum()) >= distance;
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
