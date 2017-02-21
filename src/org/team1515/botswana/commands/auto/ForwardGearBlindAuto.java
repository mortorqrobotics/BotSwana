package org.team1515.botswana.commands.auto;

import org.team1515.botswana.commands.DriveForwardLimitSwitch;
import org.team1515.botswana.commands.ToggleGearHolder;
import org.team1515.botswana.commands.movement.DriveForwardAnglePID;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ForwardGearBlindAuto extends CommandGroup {

	static final double SPEED = -0.2;

	public ForwardGearBlindAuto() {
		addSequential(new DriveForwardAnglePID(SPEED));
		addSequential(new ToggleGearHolder());
	}

}
