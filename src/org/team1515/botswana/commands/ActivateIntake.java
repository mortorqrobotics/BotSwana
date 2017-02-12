package org.team1515.botswana.commands;

import org.team1515.botswana.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ActivateIntake extends Command {
	
	public ActivateIntake() {
		requires(Robot.intake);
	}
	
	@Override
	protected void initialize() {
		Robot.intake.start();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.intake.stop();
	}
	
	@Override
	protected void interrupted() {
		end();
	}

}
