package org.team1515.botswana.commands;

import org.team1515.botswana.Controls;
import org.team1515.botswana.commands.manipulators.ActivateIntake;
import org.team1515.botswana.commands.manipulators.ToggleGearHolder;
import org.team1515.botswana.commands.manipulators.WinchLift;
import org.team1515.botswana.commands.movement.Drive;
import org.team1515.botswana.util.WheelSpeeds;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutomatedTest extends CommandGroup {

	Command delayCommand = new Delay();

	public AutomatedTest() {
		testCommand(new ActivateIntake(), "intake", 3);
		testCommand(new ToggleGearHolder(), "open gear holder", 3);
		testCommand(new ToggleGearHolder(), "close gear holder", 3);
		testCommand(new WinchLift(), "spin winch", 3);
		testCommand(new Drive(new WheelSpeeds(1, 1, 1, 1), 3), "drive forward", 3);
		testCommand(new Drive(new WheelSpeeds(-1, -1, -1, -1), 3), "drive backward", 3);
		testCommand(new Drive(new WheelSpeeds(-1, 1, -1, 1), 3), "rotate left", 3);
		testCommand(new Drive(new WheelSpeeds(1, -1, 1, -1), 3), "rotate right", 3);
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
