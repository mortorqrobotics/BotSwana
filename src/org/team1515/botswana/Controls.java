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
	
	public static final Button REVERSE = new JoystickButton(Robot.driveStick, 15);
	public static final Button UN_REVERSE = new JoystickButton(Robot.driveStick, 7);
	public static final Button LOADING_STATION = new JoystickButton(Robot.driveStick, 4);
	public static final Button GEAR_UNLOAD = new JoystickButton(Robot.driveStick, 3);
	public static final Button FIX_DIRECTION = new JoystickButton(Robot.driveStick, 2);
	
	public static final Button INTAKE = new CombinedButton(Robot.secondStick, 3, 5);
	public static final Button SHOOT = new CombinedButton(Robot.secondStick, 4, 6);
	public static final Button INTAKE_REVERSE = new JoystickButton(Robot.secondStick, 11);
	public static final Button SHOOT_REVERSE = new JoystickButton(Robot.secondStick, 12);
	public static final Button TOGGLE_GEAR_HOLDER = new JoystickButton(Robot.secondStick, 2);
	public static final Button ALIGN = new JoystickButton(Robot.driveStick, 4);
	public static final Button LIFT = new JoystickButton(Robot.secondStick, 1);
	public static final Button TEST = new JoystickButton(Robot.secondStick, 90);

}
