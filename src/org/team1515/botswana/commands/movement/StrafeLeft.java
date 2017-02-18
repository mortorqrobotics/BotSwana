package org.team1515.botswana.commands.movement;

import org.team1515.botswana.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StrafeLeft extends Command {

	double speed;
	
    public StrafeLeft(double speed, int time) {
    	requires(Robot.driveTrain);
    	this.speed = speed;
    	setTimeout(time);
    }
    
    protected void initialize() {
		Robot.driveTrain.moveLeft(speed);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
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

