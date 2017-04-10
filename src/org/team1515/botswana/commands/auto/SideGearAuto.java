package org.team1515.botswana.commands.auto;

import org.team1515.botswana.Robot;
import org.team1515.botswana.commands.ActionCommand;
import org.team1515.botswana.commands.Delay;
import org.team1515.botswana.commands.manipulators.OpenGearHolder;
import org.team1515.botswana.commands.movement.DriveForwardAnglePID;
import org.team1515.botswana.commands.movement.DriveUntilDistance;
import org.team1515.botswana.commands.movement.TurnAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SideGearAuto extends CommandGroup {

	public SideGearAuto(double angle) {
		addSequential(new OpenGearHolder());
		addSequential(new ActionCommand(() -> {
			Robot.driveTrain.setBrakeMode(true);
		}));
		addSequential(new DriveUntilDistance(-0.25, 710));
		addSequential(new Delay(0.5));
		addSequential(new ActionCommand(() -> {
			Robot.driveTrain.setBrakeMode(false);
		}));
//		addSequential(new TurnAngle(angle), 2);
//		addSequential(new Delay(0.5));
//		addSequential(new DriveForwardAnglePID(-0.25, 0), 2);
	}

}
