package org.team1515.botswana.commands.movement;

import org.team1515.botswana.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardLimitSwitch extends Command {

	double speed;

	public DriveForwardLimitSwitch(double speed) {
		requires(Robot.driveTrain);
		this.speed = speed;
	}

	protected void execute() {
		Robot.driveTrain.moveForward(speed);
	}

	protected boolean isFinished() {
		return !Robot.limitSwitch.get();
	}

	protected void end() {
		Robot.driveTrain.stop();
	}

	protected void interrupted() {
		end();
	}
}
