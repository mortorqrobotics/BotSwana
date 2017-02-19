package org.team1515.botswana.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class CombinedButton extends Button {

	Joystick joystick;
	int[] buttons;
	
	public CombinedButton(Joystick joystick, int... buttons) {
		this.joystick = joystick;
		this.buttons = buttons;
	}

	@Override
	public boolean get() {
		for (int button : buttons) {
			if (joystick.getRawButton(button)) {
				return true;
			}
		}
		return false;
	}
	
}
