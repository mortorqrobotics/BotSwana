package org.team1515.botswana.commands;

import org.team1515.botswana.Controls;
import org.team1515.botswana.Robot;
import org.team1515.botswana.commands.movement.DriveBackward;
import org.team1515.botswana.commands.movement.DriveForward;
import org.team1515.botswana.commands.movement.RotateLeft;
import org.team1515.botswana.commands.movement.RotateRight;
import org.team1515.botswana.commands.movement.StrafeLeft;
import org.team1515.botswana.commands.movement.StrafeRight;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutomatedTest extends CommandGroup {

	Command delayCommand = new Delay();

	public AutomatedTest() {
		testCommand(new ActivateIntake(), "intake", 3);
		testCommand(new ToggleGearHolder(), "open gear holder", 3);
		testCommand(new ToggleGearHolder(), "close gear holder", 3);
		testCommand(new WinchLift(), "spin winch", 3);
		testCommand(new DriveForward(1, 3), "drive forward", 3);
		testCommand(new DriveBackward(1, 3), "drive backward", 3);
		testCommand(new StrafeLeft(1, 3), "strafe left", 3);
		testCommand(new StrafeRight(1, 3), "strafe right", 3);
		testCommand(new RotateLeft(1, 3), "rotate left", 3);
		testCommand(new RotateRight(1, 3), "rotate right", 3);
	}
	
	public void testCommand(Command command, String description, int timeout) {
		addSequential(new ActionCommand(() -> {
			System.out.println("Testing " + description);
		}));
		addSequential(command, timeout);
		addSequential(delayCommand);
		Controls.TEST.cancelWhenPressed(delayCommand);
	}	
	
	public void testCommand(Command command, String description) {
		addSequential(new ActionCommand(() -> {
			System.out.println("Testing " + description);
		}));
		addSequential(command);
		addSequential(delayCommand);
		Controls.TEST.cancelWhenPressed(delayCommand);

	}

}
