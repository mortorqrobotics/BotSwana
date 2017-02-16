package org.team1515.botswana.commands.auto;

import org.team1515.botswana.commands.Align;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ForwardGearSightAuto extends CommandGroup {
	
	static final double SPEED = 1;
	static final double DISTANCE = 212423;
	
	public ForwardGearSightAuto() {
		addSequential(new DriveForwardAuto(SPEED, DISTANCE));
		addSequential(new Align());
	}
}
