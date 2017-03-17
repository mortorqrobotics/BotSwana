package org.team1515.botswana;

import org.team1515.botswana.commands.ActionCommand;
import org.team1515.botswana.commands.manipulators.ActivateIntake;
import org.team1515.botswana.commands.manipulators.ReverseDrive;
import org.team1515.botswana.commands.manipulators.ReverseIntake;
import org.team1515.botswana.commands.manipulators.ReverseShoot;
import org.team1515.botswana.commands.manipulators.Shoot;
import org.team1515.botswana.commands.manipulators.ToggleGearHolder;
import org.team1515.botswana.commands.manipulators.WinchLift;
import org.team1515.botswana.commands.movement.Drive;
import org.team1515.botswana.util.WheelSpeeds;

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
		
		// after slamming into the loading station with the wall with the robot,
		// back up the correct amount so the gear can go in the gear holder
		Controls.LOADING_STATION.whenPressed(new Drive(new WheelSpeeds(0.15, 0.15, 0.15, 0.15), 0.3));
		
		Controls.LIFT.whenPressed(new WinchLift());
		
//		Controls.TEST.whenPressed(new AutomatedTest());
	}

}
