package org.team1515.botswana.commands.auto;

import org.team1515.botswana.commands.manipulators.ToggleGearHolder;
import org.team1515.botswana.commands.movement.Align;
import org.team1515.botswana.commands.movement.DriveForwardDistance;
import org.team1515.botswana.commands.movement.TurnAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftGearSightAuto extends CommandGroup {

	static final double DISTANCE_1 = 133;
	static final double DISTANCE_2 = 3432;
	static final double ANGLE = 30;

	public LeftGearSightAuto() {
		addSequential(new DriveForwardDistance(DISTANCE_1));
		addSequential(new TurnAngle(ANGLE));
		addSequential(new DriveForwardDistance(DISTANCE_2));
		addSequential(new Align());
		addSequential(new ToggleGearHolder());
	}
}
