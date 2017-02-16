package org.team1515.botswana.commands.auto;

import org.team1515.botswana.commands.DriveForwardLimitSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ForwardGearBlindAuto extends CommandGroup {

	static final double SPEED = 1;

	public ForwardGearBlindAuto() {
		addSequential(new DriveForwardLimitSwitch(SPEED));
	}

}
