package org.team1515.botswana.commands.auto;

import org.team1515.botswana.commands.Align;
import org.team1515.botswana.commands.DriveForwardDistanceBad;
import org.team1515.botswana.commands.TurnAngleBad;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightGearSightAuto extends CommandGroup {

	static final int DISTANCE_1 = 133;
	static final int DISTANCE_2 = 234;
	static final double ANGLE = -30;

	public RightGearSightAuto() {
		addSequential(new DriveForwardDistanceBad(DISTANCE_1));
		addSequential(new TurnAngleBad(ANGLE));
		addSequential(new DriveForwardDistanceBad(DISTANCE_2));
		addSequential(new Align());
	}
}
