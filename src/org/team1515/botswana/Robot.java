
package org.team1515.botswana;

import org.team1515.botswana.subsystems.GearHolder;
import org.team1515.botswana.subsystems.KliveDrive;
import org.team1515.botswana.subsystems.MecanumDrive;
import org.team1515.botswana.subsystems.MecanumWheel;
import org.team1515.botswana.subsystems.Shooter;
import org.team1515.botswana.subsystems.Winch;
import org.team1515.botswana.util.WheelSpeeds;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
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
	
	public static final PowerDistributionPanel pdp = new PowerDistributionPanel(10);

	@Override
	public void robotInit() {
		oi = new OI();
//		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		
		SmartDashboard.putNumber("p", MecanumWheel.K_P);
		SmartDashboard.putNumber("i", MecanumWheel.K_I);
		SmartDashboard.putNumber("d", MecanumWheel.K_D);
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
		
		MecanumWheel.K_P = SmartDashboard.getNumber("p", 0);
		MecanumWheel.K_I = SmartDashboard.getNumber("i", 0);
		MecanumWheel.K_D = SmartDashboard.getNumber("d", 0);
	}

	BuiltInAccelerometer acceler = new BuiltInAccelerometer();
	
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putBoolean("isReversed", Robot.driveTrain.isReversed());
//		System.out.println("gyro: " + gyro.getRate());
		Scheduler.getInstance().run();
		
//		for(int i : new int[]{2,3,12,13}) {
//			System.out.print(pdp.getCurrent(i) + "\t");
//		}
//		System.out.println();
//		System.out.println();
//		SmartDashboard.putNumber("errorTL", pdp.getCurrent(3) + Math.random() / 10000);
//		SmartDashboard.putNumber("errorTR", pdp.getCurrent(2) + Math.random() / 10000);
//		SmartDashboard.putNumber("errorBL", pdp.getCurrent(13) + Math.random() / 10000);
//		SmartDashboard.putNumber("errorBR", pdp.getCurrent(12) + Math.random() / 10000);

		SmartDashboard.putNumber("errorTL", driveTrain.topLeftWheel.error + Math.random() / 10000);
		SmartDashboard.putNumber("errorTR", driveTrain.topRightWheel.error + Math.random() / 10000);
		SmartDashboard.putNumber("errorBL", driveTrain.bottomLeftWheel.error + Math.random() / 10000);
		SmartDashboard.putNumber("errorBR", driveTrain.bottomRightWheel.error + Math.random() / 10000);
		
		WheelSpeeds rates = driveTrain.getEncoderRates();
		SmartDashboard.putNumber("encTL", Math.abs(rates.topLeft) + Math.random() / 10000);
		SmartDashboard.putNumber("encTR", Math.abs(rates.topRight) + Math.random() / 10000);
		SmartDashboard.putNumber("encBL", Math.abs(rates.bottomLeft) + Math.random() / 10000);
		SmartDashboard.putNumber("encBR", Math.abs(rates.bottomRight) + Math.random() / 10000);
		
//		System.out.println((acceler.getX() - x0) + "\t" + (acceler.getY() - y0) + "\t" + (acceler.getZ() - z0));
	}

	@Override
	public void testPeriodic() {

	}
}
