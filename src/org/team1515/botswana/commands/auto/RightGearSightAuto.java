package org.team1515.botswana.commands.auto;

import org.team1515.botswana.commands.Align;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightGearSightAuto extends CommandGroup {
	
	static final double DISTANCE = 133;
	static final double SPEED = 133;
	static final double ANGLE = -30;

	public RightGearSightAuto() {
		addSequential(new DriveForwardAuto(SPEED, DISTANCE));
		addSequential(new Align(ANGLE));
		addSequential(new Align());
	}
}
