package org.team1515.botswana.commands.auto;

import org.team1515.botswana.commands.manipulators.ToggleGearHolder;
import org.team1515.botswana.commands.movement.DriveForwardDistance;
import org.team1515.botswana.commands.movement.DriveForwardLimitSwitch;
import org.team1515.botswana.commands.movement.TurnAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightGearBlindAuto extends CommandGroup {

	static final double DISTANCE = 133;
	static final double SPEED = 133;
	static final double ANGLE = -30;

	public RightGearBlindAuto() {
		addSequential(new DriveForwardDistance(DISTANCE));
		addSequential(new TurnAngle(ANGLE));
		addSequential(new DriveForwardLimitSwitch(SPEED));
		addSequential(new ToggleGearHolder());
	}
}
