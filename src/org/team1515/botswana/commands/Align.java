package org.team1515.botswana.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Align extends CommandGroup {

	static final double FORWARD_SPEED = 0.09;
	static final int ITERATIONS = 2;

	public Align() {
		for (int i = 0; i < ITERATIONS; i++) {
			SendPiRequest req = new SendPiRequest();
			addSequential(req);
			if (req.getAngleResponse() != null) {
				addSequential(new TurnAngle(req.getAngleResponse()));  // only turn angle if angle found
			} else if (i > 0) {
				addSequential(new DriveForwardLimitSwitch(FORWARD_SPEED)); // if no angle found, but aligned in previous iterations, drive forward and end
				return;
			} else {
				return; // if no angle found the first time, end command
			}
		}
		addSequential(new DriveForwardLimitSwitch(FORWARD_SPEED));
	}
}
