package org.team1515.botswana.commands.auto;

import org.team1515.botswana.commands.manipulators.ToggleGearHolder;
import org.team1515.botswana.commands.movement.Align;
import org.team1515.botswana.commands.movement.Drive;
import org.team1515.botswana.commands.movement.TurnAngle;
import org.team1515.botswana.util.WheelSpeeds;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftGearSightAuto extends CommandGroup {

	static final double TIMEOUT_1 = 3;
	static final double TIMEOUT_2 = 2;
	static final double ANGLE = 30;
	static final double SPEED = 0.5;

	public LeftGearSightAuto() {
		addSequential(new Drive(new WheelSpeeds(SPEED, SPEED, SPEED, SPEED), TIMEOUT_1));
		addSequential(new TurnAngle(ANGLE));
		addSequential(new Drive(new WheelSpeeds(SPEED, SPEED, SPEED, SPEED), TIMEOUT_2));
		addSequential(new Align());
		addSequential(new ToggleGearHolder());
	}
}
