package org.team1515.botswana.commands;

import edu.wpi.first.wpilibj.command.Command;

public class Delay extends Command {
	
	public Delay() {
		
	}
	
	public Delay(double time) {
		setTimeout(time);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
