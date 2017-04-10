package org.team1515.botswana.commands.movement;

import org.team1515.botswana.Robot;
import org.team1515.botswana.util.WheelSpeeds;

import edu.wpi.first.wpilibj.command.Command;

public class DriveUntilDistance extends Command {
	
	double baseSpeed;
	double distance;
	double startAngle;
	
	public DriveUntilDistance(double baseSpeed, int distance) {
		requires(Robot.driveTrain);
		this.baseSpeed = baseSpeed;
		this.distance = distance;
	}
	
	@Override
	protected void initialize() {
		startAngle = Robot.gyro.getAngle();
	}
	
	@Override
	protected void execute() {
		double error = startAngle - Robot.gyro.getAngle();
		double speed = error * 0.000000001;
		Robot.driveTrain.setSpeed(new WheelSpeeds(speed + baseSpeed, -speed + baseSpeed,
				speed + baseSpeed, -speed + baseSpeed));
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut() || Robot.distanceSensor.getAverageValue() >= distance;
	}
	
	@Override
	protected void end() {
		Robot.driveTrain.stop();
	}

}
