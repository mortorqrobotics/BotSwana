package org.team1515.botswana.commands.auto;

import org.team1515.botswana.commands.manipulators.ToggleGearHolder;
import org.team1515.botswana.commands.movement.Align;
import org.team1515.botswana.commands.movement.DriveForwardDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ForwardGearSightAuto extends CommandGroup {

	static final double DISTANCE = 212423;

	public ForwardGearSightAuto() {
		addSequential(new DriveForwardDistance(DISTANCE));
		addSequential(new Align());
		addSequential(new ToggleGearHolder());
	}
}
