package org.team1515.botswana.commands.auto;

import org.team1515.botswana.commands.manipulators.ToggleGearHolder;
import org.team1515.botswana.commands.movement.DriveForwardAnglePID;
import org.team1515.botswana.commands.movement.TurnAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightGearBlindAuto extends CommandGroup {

	static final double TIMEOUT_1 = 5;
	public final double TIMEOUT_2 = 2;
	static final double SPEED = -0.2;
	static final double ANGLE = -30;

	public RightGearBlindAuto() {
		addSequential(new DriveForwardAnglePID(SPEED, 0), TIMEOUT_1);
		addSequential(new TurnAngle(ANGLE));
		addSequential(new DriveForwardAnglePID(SPEED, 0), TIMEOUT_2);
		addSequential(new ToggleGearHolder());
	}
}
