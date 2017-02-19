package org.team1515.botswana.commands;

import org.team1515.botswana.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleGearHolder extends Command {

	public ToggleGearHolder() {
		requires(Robot.gearHolder);
	}

	@Override
	protected void initialize() {
		Robot.gearHolder.toggle();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
