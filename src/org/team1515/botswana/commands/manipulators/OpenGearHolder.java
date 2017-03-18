package org.team1515.botswana.commands.manipulators;

import org.team1515.botswana.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class OpenGearHolder extends Command {
	
	public OpenGearHolder() {
		requires(Robot.gearHolder);
	}
	
	@Override
	protected void initialize() {
		Robot.gearHolder.open();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
