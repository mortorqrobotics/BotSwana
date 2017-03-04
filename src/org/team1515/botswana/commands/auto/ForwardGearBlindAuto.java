package org.team1515.botswana.commands.auto;

import org.team1515.botswana.commands.Delay;
import org.team1515.botswana.commands.manipulators.ToggleGearHolder;
import org.team1515.botswana.commands.movement.Drive;
import org.team1515.botswana.commands.movement.DriveForwardAnglePID;
import org.team1515.botswana.util.WheelSpeeds;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ForwardGearBlindAuto extends CommandGroup {

	static final double PID_SPEED = -0.1;
	static final double FORWARD_SPEED = -0.5;
	
	static final double PID_TIMEOUT = 8;
	static final double FORWARD_TIMROUT = 0.5;
	static final double DELAY = 0.5;
	
	public ForwardGearBlindAuto() {
		addSequential(new DriveForwardAnglePID(PID_SPEED, 0), PID_TIMEOUT);
		addSequential(new Drive(new WheelSpeeds(FORWARD_SPEED, FORWARD_SPEED, FORWARD_SPEED, FORWARD_SPEED), 0.5));
		addSequential(new Delay(DELAY));
		addSequential(new ToggleGearHolder());
	}

}
