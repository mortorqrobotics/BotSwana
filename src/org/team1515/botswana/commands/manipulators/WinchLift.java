package org.team1515.botswana.commands.manipulators;

import org.team1515.botswana.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class WinchLift extends Command {

	public WinchLift() {
		requires(Robot.winch);
	}

	@Override
	protected void initialize() {
		Robot.winch.lift();
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		Robot.winch.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}

