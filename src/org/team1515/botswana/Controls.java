package org.team1515.botswana;

import org.team1515.botswana.util.CombinedButton;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Controls {

	public static final int JOYSTICK_DRIVE = 0;
	public static final int JOYSTICK_SECONDARY = 1;
	
	// Drive stick axes
	public static final int AXIS_FORWARD = 1;
	public static final int AXIS_SIDE = 0;
	public static final int AXIS_TWIST = 5;
	public static final int AXIS_THROTTLE = 2;
	
	public static final Button REVERSE = new JoystickButton(Robot.driveStick, 4);

	public static final Button INTAKE = new CombinedButton(Robot.secondStick, 3, 5);
	public static final Button SHOOT = new CombinedButton(Robot.secondStick, 4, 6);
	public static final Button TOGGLE_GEAR_HOLDER = new JoystickButton(Robot.secondStick, 11);
	// useless
//	public static final Button ALIGN = 7;
//	public static final Button LIFT = 3;
//	public static final Button STOP_ALIGN = 6;

}
