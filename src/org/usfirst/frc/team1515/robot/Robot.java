
package org.usfirst.frc.team1515.robot;

import org.usfirst.frc.team1515.robot.subsystems.KliveDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static Joystick joystick = new Joystick(0);
	public static final KliveDrive driveTrain = new KliveDrive(joystick);
	public static final Gyro gyro = new ADXRS450_Gyro();
	public static final DigitalInput limitSwitch = new DigitalInput(0);
	
	@Override
	public void robotInit() {
		oi = new OI();
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {

	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {

	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {

	}
}
