package org.team1515.botswana.commands.auto;

import org.team1515.botswana.commands.manipulators.ToggleGearHolder;
import org.team1515.botswana.commands.movement.Drive;
import org.team1515.botswana.commands.movement.DriveForwardLimitSwitch;
import org.team1515.botswana.commands.movement.TurnAngle;
import org.team1515.botswana.util.WheelSpeeds;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightGearBlindAuto extends CommandGroup {

	static final double TIMEOUT = 3;
	static final double SPEED = 0.5;
	static final double ANGLE = -30;

	public RightGearBlindAuto() {
		addSequential(new Drive(new WheelSpeeds(SPEED, SPEED, SPEED, SPEED), TIMEOUT));
		addSequential(new TurnAngle(ANGLE));
		addSequential(new DriveForwardLimitSwitch(SPEED));
		addSequential(new ToggleGearHolder());
	}
}
