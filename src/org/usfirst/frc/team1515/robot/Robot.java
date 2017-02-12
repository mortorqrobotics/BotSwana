
package org.usfirst.frc.team1515.robot;

import org.usfirst.frc.team1515.robot.subsystems.KliveDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static Joystick joystick = new Joystick(0);
	public static Joystick rotateStick = new Joystick(1);
	public static final KliveDrive driveTrain = new KliveDrive(joystick, rotateStick);
	public static final Gyro gyro = new ADXRS450_Gyro();
	public static final DigitalInput limitSwitch = new DigitalInput(0);
	public DoubleSolenoid sol;
	public Encoder encoder;
	
	@Override
	public void robotInit() {
		oi = new OI();
		sol = new DoubleSolenoid(14, 3, 4);
		encoder = new Encoder(1, 2, true, Encoder.EncodingType.k4X);
		encoder.setMaxPeriod(.05);
		encoder.setMinRate(10);
		encoder.setDistancePerPulse(1);
		encoder.setSamplesToAverage(10);
		encoder.reset();
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
		System.out.println(encoder.get());
	}

	@Override
	public void testPeriodic() {

	}
}
