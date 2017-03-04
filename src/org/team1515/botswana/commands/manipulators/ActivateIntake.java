package org.team1515.botswana.commands.manipulators;

import org.team1515.botswana.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ActivateIntake extends Command {

	public ActivateIntake() {
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		Robot.shooter.intake();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.shooter.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
