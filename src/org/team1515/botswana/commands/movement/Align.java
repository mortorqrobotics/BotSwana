package org.team1515.botswana.commands.movement;

import org.team1515.botswana.util.SendPiRequest;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Align extends CommandGroup {

	static final double FORWARD_SPEED = 0.09;
	static final int ITERATIONS = 1;
	
	public Align() {
		for (int i = 0; i < ITERATIONS; i++) {
			SendPiRequest req = new SendPiRequest();
			Double angle = req.getAngleResponse();
			if (angle != null) {
				addSequential(new TurnAngle(angle));  // only turn angle if angle found
			} else {
				return; // if no angle found, stop trying to align 
			}
		}
		addSequential(new DriveForwardLimitSwitch(FORWARD_SPEED));
	}
}
