package org.team1515.botswana.commands.movement;

import org.team1515.botswana.Robot;
import org.team1515.botswana.util.WheelSpeeds;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnAbsoluteAngle extends Command {
	
	double targetAbsAngle;
	double targetAngle;
	double startAngle;
	
	public TurnAbsoluteAngle(double targetAbsAngle) {
		requires(Robot.driveTrain);
		this.targetAbsAngle = targetAbsAngle;
	}
	
	@Override
	protected void initialize() {
		startAngle = Robot.gyro.getAngle();
		this.targetAngle = Robot.gyroAngleAtAutoStart + targetAbsAngle;
		SmartDashboard.putBoolean("isTurning", false);
		double speed = getDirection(startAngle);
		speed *= 0.5;
		Robot.driveTrain.setSpeed(new WheelSpeeds(-speed, speed, -speed, speed));
	}
	
	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return getDirection(Robot.gyro.getAngle()) != getDirection(startAngle);
	}
	
	@Override
	protected void end() {
		SmartDashboard.putBoolean("isTurning", true); // false to make it red
		Robot.driveTrain.stop();
	}
	
	@Override
	protected void interrupted() {
		end();
	}
	
	private double angleDiff(double a, double b) {
		double angle = ((a - b) % 360 + 360) % 360;
		if (angle > 180) {
			angle -= 360;
		}
		return angle;
	}
	
	private double getDirection(double angle) {
		return Math.signum(angleDiff(angle, targetAngle));
	}

}
