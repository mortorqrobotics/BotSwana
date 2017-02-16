package org.team1515.botswana.commands;

import org.team1515.botswana.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class JoystickDrive extends Command {

	public JoystickDrive() {
		requires(Robot.driveTrain);
	}

	protected void execute() {
		Robot.driveTrain.drive();
	}

	protected boolean isFinished() {
		return false;
	}

	protected void interrupted() {
		end();
	}
}
