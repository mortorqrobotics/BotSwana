package org.team1515.botswana.commands.auto;

import org.team1515.botswana.commands.manipulators.OpenGearHolder;
import org.team1515.botswana.commands.movement.DriveForwardAnglePID;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ForwardGearBlindAuto extends CommandGroup {

	static final double PID_SPEED = -0.15;
	
	static final double PID_TIMEOUT = 6.8;
	
	public ForwardGearBlindAuto() {
		addParallel(new OpenGearHolder());
		addSequential(new DriveForwardAnglePID(PID_SPEED, 0), PID_TIMEOUT);
//		addSequential(new Drive(new WheelSpeeds(FORWARD_SPEED, FORWARD_SPEED, FORWARD_SPEED, FORWARD_SPEED), 0.8));
	}

}
