package org.team1515.botswana.commands.movement;

import org.team1515.botswana.Robot;
import org.team1515.botswana.util.WheelSpeeds;

import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command {

	WheelSpeeds speeds;
	
    public Drive(WheelSpeeds speeds, double time) {
    	requires(Robot.driveTrain);
    	this.speeds = speeds;
    	setTimeout(time);
    }
    
	@Override
    protected void initialize() {
		Robot.driveTrain.setSpeed(speeds);
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
