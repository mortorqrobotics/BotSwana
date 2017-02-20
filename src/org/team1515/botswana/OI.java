package org.team1515.botswana;

import org.team1515.botswana.commands.ActivateIntake;
import org.team1515.botswana.commands.ReverseDrive;
import org.team1515.botswana.commands.ReverseIntake;
import org.team1515.botswana.commands.ReverseShoot;
import org.team1515.botswana.commands.Shoot;
import org.team1515.botswana.commands.ToggleGearHolder;

public class OI {

	public OI() {

//		Command alignCommand = new Align();
//
//		Button align = new JoystickButton(Robot.secondStick, Controls.BUTTON_ALIGN);
//		align.whenPressed(alignCommand);
//
//		Button stopAlign = new JoystickButton(Robot.secondStick, Controls.BUTTON_STOP_ALIGN);
//		stopAlign.cancelWhenPressed(alignCommand);

//		Button lift = new JoystickButton(Robot.secondStick, Controls.BUTTON_LIFT);
//		lift.whenPressed(new WinchLift());

		Controls.INTAKE.whileHeld(new ActivateIntake());

		Controls.SHOOT.whileHeld(new Shoot());
		
		Controls.SHOOT_REVERSE.whileHeld(new ReverseShoot());
		
		Controls.INTAKE_REVERSE.whileHeld(new ReverseIntake());
		
		Controls.TOGGLE_GEAR_HOLDER.whenPressed(new ToggleGearHolder());
		
		Controls.REVERSE.whenPressed(new ReverseDrive());

//		Controls.TEST.whenPressed(new AutomatedTest());
	}

}
