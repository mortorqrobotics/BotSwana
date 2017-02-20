
package org.team1515.botswana;

import org.team1515.botswana.subsystems.GearHolder;
import org.team1515.botswana.subsystems.KliveDrive;
import org.team1515.botswana.subsystems.MecanumDrive;
import org.team1515.botswana.subsystems.Shooter;
import org.team1515.botswana.subsystems.Winch;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static Joystick driveStick = new Joystick(Controls.JOYSTICK_DRIVE);
	public static Joystick secondStick = new Joystick(Controls.JOYSTICK_SECONDARY);

	public static final MecanumDrive driveTrain = new KliveDrive();
	public static final GearHolder gearHolder = new GearHolder();
	public static final Winch winch = new Winch();
	public static final Shooter shooter = new Shooter();

	public static final Gyro gyro = new ADXRS450_Gyro();
	public static final DigitalInput limitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH);

	@Override
	public void robotInit() {
		oi = new OI();
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	}

	@Override
	public void disabledInit() {
		Robot.driveTrain.resetPID();
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

	double x0, y0, z0;
	@Override
	public void teleopInit() {
		x0 = acceler.getX();
		y0 = acceler.getY();
		z0 = acceler.getZ();
	}

	BuiltInAccelerometer acceler = new BuiltInAccelerometer();
	
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putBoolean("isReversed", Robot.driveTrain.isReversed());
//		System.out.println("gyro: " + gyro.getRate());
		Scheduler.getInstance().run();
		
		System.out.println((acceler.getX() - x0) + "\t" + (acceler.getY() - y0) + "\t" + (acceler.getZ() - z0));
	}

	@Override
	public void testPeriodic() {

	}
}
