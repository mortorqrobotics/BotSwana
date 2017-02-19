package org.team1515.botswana.commands;

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
	
	public AutomatedTest() {
		// TODO: fix
//		testCommand(new ActivateIntake(), "intake", 3, Robot.secondStick.getRawButton(BUTTON_TEST));
//		testCommand(new ToggleGearHolder(), "open gear holder", 3, Robot.secondStick.getRawButton(BUTTON_TEST));
//		testCommand(new ToggleGearHolder(), "close gear holder", 3, Robot.secondStick.getRawButton(BUTTON_TEST));
//		testCommand(new WinchLift(), "spin winch", 3, Robot.secondStick.getRawButton(BUTTON_TEST));
//		testCommand(new DriveForward(1, 3), "drive forward", 3, Robot.secondStick.getRawButton(BUTTON_TEST));
//		testCommand(new DriveBackward(1, 3), "drive backward", 3, Robot.secondStick.getRawButton(BUTTON_TEST));
//		testCommand(new StrafeLeft(1, 3), "strafe left", 3, Robot.secondStick.getRawButton(BUTTON_TEST));
//		testCommand(new StrafeRight(1, 3), "strafe right", 3, Robot.secondStick.getRawButton(BUTTON_TEST));
//		testCommand(new RotateLeft(1, 3), "rotate left", 3, Robot.secondStick.getRawButton(BUTTON_TEST));
//		testCommand(new RotateRight(1, 3), "rotate right", 3, Robot.secondStick.getRawButton(BUTTON_TEST));
	}
	
	public void testCommand(Command command, String description, int timeout, boolean isPressed) {
		if (isPressed) {
			addSequential(new ActionCommand(() -> {
				System.out.println("Testing " + description);
			}));
			addSequential(command, timeout);
			addSequential(new Delay(1));
		}
	}	
	
	public void testCommand(Command command, String description) {
		addParallel(new ActionCommand(() -> {
			System.out.println("Testing " + description);
		}));
		addParallel(command);
		addSequential(new Delay(1));
	
	}
}
