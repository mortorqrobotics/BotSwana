package org.team1515.botswana.commands;

import org.team1515.botswana.commands.movement.DriveBackward;
import org.team1515.botswana.commands.movement.DriveForward;
import org.team1515.botswana.commands.movement.RotateLeft;
import org.team1515.botswana.commands.movement.RotateRight;
import org.team1515.botswana.commands.movement.StrafeLeft;
import org.team1515.botswana.commands.movement.StrafeRight;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutomatedTest extends CommandGroup {
	
	public AutomatedTest() {
		testCommand(new ActivateIntake(), "intake", 3);
		testCommand(new ToggleGearHolder(), "open gear holder");
		testCommand(new ToggleGearHolder(), "close gear holder");
		testCommand(new WinchLift(), "spin winch");
		testCommand(new DriveForward(1, 3), "drive forward");
		testCommand(new DriveBackward(1, 3), "drive backward");
		testCommand(new StrafeLeft(1, 3), "strafe left");
		testCommand(new StrafeRight(1, 3), "strafe right");
		testCommand(new RotateLeft(1, 3), "rotate left");
		testCommand(new RotateRight(1, 3), "rotate right");
	}
	
	public void testCommand(Command command, String description, int timeout) {
		addSequential(new ActionCommand(() -> {
			System.out.println("Testing " + description);
		}));
		addSequential(command, timeout);
		addSequential(new Delay(1));
	}	
	
	public void testCommand(Command command, String description) {
		addParallel(new ActionCommand(() -> {
			System.out.println("Testing " + description);
		}));
		addParallel(command);
		addSequential(new Delay(1));
	
	}
}
