package org.team1515.botswana.commands.manipulators;

import org.team1515.botswana.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ReverseDrive extends Command {
	
	public ReverseDrive() {
		requires(Robot.driveTrain);
	}
	
	@Override
	protected void initialize() {
		Robot.driveTrain.reverse();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
	
	@Override
	protected void interrupted() {
		end();
	}

}
