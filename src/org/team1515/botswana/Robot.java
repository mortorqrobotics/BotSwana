
package org.team1515.botswana;

import org.team1515.botswana.subsystems.GearHolder;
import org.team1515.botswana.subsystems.Intake;
import org.team1515.botswana.subsystems.KliveDrive;
import org.team1515.botswana.subsystems.MecanumDrive;
import org.team1515.botswana.subsystems.Shooter;
import org.team1515.botswana.subsystems.Winch;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static Joystick driveStick = new Joystick(Controls.JOYSTICK_DRIVE);
	public static Joystick secondStick = new Joystick(Controls.JOYSTICK_SECONDARY);

	public static final MecanumDrive driveTrain = new KliveDrive();
	public static final Intake intake = new Intake();
	public static final GearHolder gearHolder = new GearHolder();
	public static final Winch winch = new Winch();
	public static final Shooter shooter = new Shooter();

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
