package org.team1515.botswana.commands.auto;

import org.team1515.botswana.commands.Align;
import org.team1515.botswana.commands.DriveForwardDistance;
import org.team1515.botswana.commands.TurnAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightGearSightAuto extends CommandGroup {
	
	static final double DISTANCE_1 = 133;
	static final double DISTANCE_2 = 234;
	static final double ANGLE = -30;

	public RightGearSightAuto() {
		addSequential(new DriveForwardDistance(DISTANCE_1));
		addSequential(new TurnAngle(ANGLE));
		addSequential(new DriveForwardDistance(DISTANCE_2));
		addSequential(new Align());
	}
}
