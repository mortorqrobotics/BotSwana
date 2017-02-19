package org.team1515.botswana.commands.movement;

import org.team1515.botswana.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveBackward extends Command {

	double speed;
	
    public DriveBackward(double speed, int time) {
    	requires(Robot.driveTrain);
    	this.speed = speed;
    	setTimeout(time);
    }
    
	@Override
    protected void initialize() {
		Robot.driveTrain.moveBackward(speed);
	}

	@Override
	protected void execute() {
		Robot.driveTrain.getEncoderRates().print();
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
