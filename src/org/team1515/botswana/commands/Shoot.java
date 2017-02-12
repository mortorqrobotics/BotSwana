package org.team1515.botswana.commands;

import org.team1515.botswana.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command {
	
	public Shoot() {
		requires(Robot.shooter);
	}
	
	@Override
	protected void initialize() {
		Robot.shooter.shoot();
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
