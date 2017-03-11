package org.team1515.botswana;

import org.team1515.botswana.commands.ActionCommand;
import org.team1515.botswana.commands.AutomatedTest;
import org.team1515.botswana.commands.auto.RightGearSightAuto;
import org.team1515.botswana.commands.manipulators.ActivateIntake;
import org.team1515.botswana.commands.manipulators.ReverseDrive;
import org.team1515.botswana.commands.manipulators.ReverseIntake;
import org.team1515.botswana.commands.manipulators.ReverseShoot;
import org.team1515.botswana.commands.manipulators.Shoot;
import org.team1515.botswana.commands.manipulators.ToggleGearHolder;
import org.team1515.botswana.commands.manipulators.WinchLift;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	public OI() {

		Controls.INTAKE.whileHeld(new ActivateIntake());

		Controls.SHOOT.whileHeld(new Shoot());
		
		Controls.SHOOT_REVERSE.whileHeld(new ReverseShoot());
		
		Controls.INTAKE_REVERSE.whileHeld(new ReverseIntake());
		
		Controls.TOGGLE_GEAR_HOLDER.whenPressed(new ToggleGearHolder());
		
		Controls.REVERSE.whenPressed(new ReverseDrive());
		
		Controls.UN_REVERSE.whenPressed(new ActionCommand(()-> {
			Robot.driveTrain.undoReverse();
		}));
		
		Controls.LIFT.whenPressed(new WinchLift());
		
//		Controls.TEST.whenPressed(new AutomatedTest());
	}

}
