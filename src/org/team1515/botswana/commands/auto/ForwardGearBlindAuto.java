package org.team1515.botswana.commands.auto;

import org.team1515.botswana.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ForwardGearBlindAuto extends Command {
	
	static final double SPEED = 1;
	
	public ForwardGearBlindAuto() {
		requires(Robot.driveTrain);
	}
	
	@Override
	protected void execute() {
		Robot.driveTrain.moveForward(SPEED);
	}
	
	@Override
	protected boolean isFinished() {
		return !Robot.limitSwitch.get();
	}
	
	@Override
	protected void end() {
		Robot.driveTrain.stop();
	}
	
	@Override
	protected void interrupted() {
		end();
	}
}
