package org.team1515.botswana.commands.auto;

import org.team1515.botswana.commands.Delay;
import org.team1515.botswana.commands.ToggleGearHolder;
import org.team1515.botswana.commands.movement.DriveForward;
import org.team1515.botswana.commands.movement.DriveForwardAnglePID;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ForwardGearBlindAuto extends CommandGroup {

	static final double SPEED = -0.1;
	
	public ForwardGearBlindAuto() {
		this(0);
	}

	public ForwardGearBlindAuto(double angle) {
		addSequential(new DriveForwardAnglePID(SPEED, angle), 8);
		addSequential(new DriveForward(-0.05, 0.5));
		addSequential(new Delay(0.5));
		addSequential(new ToggleGearHolder());
	}

}
