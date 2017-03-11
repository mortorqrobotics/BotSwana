package org.team1515.botswana.commands.auto;

import org.team1515.botswana.commands.manipulators.ToggleGearHolder;

import org.team1515.botswana.commands.movement.Align;
import org.team1515.botswana.commands.movement.Drive;
import org.team1515.botswana.util.WheelSpeeds;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ForwardGearSightAuto extends CommandGroup {

	static final double SPEED = 0.5;
	static final double TIMEOUT = 3;

	public ForwardGearSightAuto() {
		addSequential(new Drive(new WheelSpeeds(SPEED, SPEED, SPEED, SPEED), TIMEOUT));
		addSequential(new Align());
		addSequential(new ToggleGearHolder());
	}
}
