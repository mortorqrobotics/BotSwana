package org.team1515.botswana;

import org.team1515.botswana.commands.ActionCommand;
import org.team1515.botswana.commands.ActivateIntake;
import org.team1515.botswana.commands.Align;
import org.team1515.botswana.commands.ReverseDrive;
import org.team1515.botswana.commands.Shoot;
import org.team1515.botswana.commands.ToggleGearHolder;
import org.team1515.botswana.commands.WinchLift;
import org.team1515.botswana.commands.AutomatedTest;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class OI {

	public OI() {

		Command alignCommand = new Align();

		Button align = new JoystickButton(Robot.secondStick, Controls.BUTTON_ALIGN);
		align.whenPressed(alignCommand);

		Button stopAlign = new JoystickButton(Robot.secondStick, Controls.BUTTON_STOP_ALIGN);
		stopAlign.cancelWhenPressed(alignCommand);

		Button intake = new JoystickButton(Robot.secondStick, Controls.BUTTON_INTAKE);
		intake.whileHeld(new ActivateIntake());

		Button lift = new JoystickButton(Robot.secondStick, Controls.BUTTON_LIFT);
		lift.whenPressed(new WinchLift());

		Button shoot = new JoystickButton(Robot.secondStick, Controls.BUTTON_SHOOT);
		shoot.whenPressed(new Shoot());
		
		Button toggleGearHolder = new JoystickButton(Robot.secondStick, Controls.TOGGLE_GEAR_HOLDER);
		toggleGearHolder.toggleWhenPressed(new ToggleGearHolder());
		
		Button reverseDrive = new JoystickButton(Robot.driveStick, Controls.REVERSE_DRIVE);
		reverseDrive.whenPressed(new ReverseDrive());

		Button automatedTest = new JoystickButton(Robot.secondStick, Controls.BUTTON_TEST);
		automatedTest.whenPressed(new AutomatedTest());
	}

}
