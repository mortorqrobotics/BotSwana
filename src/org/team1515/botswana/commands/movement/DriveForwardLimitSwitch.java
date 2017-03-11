package org.team1515.botswana.commands.movement;

import org.team1515.botswana.Robot;
import org.team1515.botswana.util.WheelSpeeds;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardLimitSwitch extends Command {

	double speed;

	public DriveForwardLimitSwitch(double speed) {
		requires(Robot.driveTrain);
		this.speed = speed;
	}

	protected void execute() {
		Robot.driveTrain.setSpeed(new WheelSpeeds(speed, speed, speed, speed));
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
