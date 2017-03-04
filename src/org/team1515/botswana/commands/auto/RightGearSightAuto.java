package org.team1515.botswana.commands.auto;

import org.team1515.botswana.commands.manipulators.ToggleGearHolder;
import org.team1515.botswana.commands.movement.Align;
import org.team1515.botswana.commands.movement.DriveForwardDistanceBad;
import org.team1515.botswana.commands.movement.TurnAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightGearSightAuto extends CommandGroup {

	static final int DISTANCE_1 = 133;
	static final int DISTANCE_2 = 234;
	static final double ANGLE = -30;

	public RightGearSightAuto() {
		addSequential(new DriveForwardDistanceBad(DISTANCE_1));
		addSequential(new TurnAngle(ANGLE));
		addSequential(new DriveForwardDistanceBad(DISTANCE_2));
		addSequential(new Align());
		addSequential(new ToggleGearHolder());
	}
}
