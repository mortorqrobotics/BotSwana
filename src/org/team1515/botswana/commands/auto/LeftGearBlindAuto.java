package org.team1515.botswana.commands.auto;

import org.team1515.botswana.commands.Align;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftGearBlindAuto extends CommandGroup {
	
	static final double DISTANCE = 133;
	static final double SPEED = 133;
	static final double ANGLE = 30;
	
	public LeftGearBlindAuto() {
		addSequential(new DriveForwardAuto(SPEED, DISTANCE));
		addSequential(new Align(ANGLE));
		addSequential(new ForwardGearBlindAuto());
	}
}
