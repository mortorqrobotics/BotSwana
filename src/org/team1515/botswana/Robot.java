
package org.team1515.botswana;

import org.team1515.botswana.commands.movement.Drive;
import org.team1515.botswana.subsystems.GearHolder;
import org.team1515.botswana.subsystems.KliveDrive;
import org.team1515.botswana.subsystems.MecanumDrive;
import org.team1515.botswana.subsystems.MecanumWheel;
import org.team1515.botswana.subsystems.Shooter;
import org.team1515.botswana.subsystems.Winch;
import org.team1515.botswana.util.WheelSpeeds;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
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
	
	public static final PowerDistributionPanel pdp = new PowerDistributionPanel(10);
	
	public static double gyroAngleAtAutoStart = 0;
	
	Command autonomousCommand;

	@Override
	public void robotInit() {
		oi = new OI();
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		
//		autonomousCommand = new ForwardGearBlindAuto();
		autonomousCommand = new Drive(new WheelSpeeds(-0.25, -0.25, -0.25, -0.25), 3);
		
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
		gyroAngleAtAutoStart = Robot.gyro.getAngle();
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		MecanumWheel.K_P = SmartDashboard.getNumber("p", 0);
		MecanumWheel.K_I = SmartDashboard.getNumber("i", 0);
		MecanumWheel.K_D = SmartDashboard.getNumber("d", 0);
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		SmartDashboard.putBoolean("isReversed", Robot.driveTrain.isReversed());
		SmartDashboard.putBoolean("limitSwitch", !Robot.gearHolder.isGearDetected());
		SmartDashboard.putBoolean("gearHolderOpen", !Robot.gearHolder.isOpen());
		
//		for(int i : new int[]{2,3,12,13}) {
//			System.out.print(pdp.getCurrent(i) + "\t");
//		}
//		System.out.println();
//		System.out.println();
//		SmartDashboard.putNumber("errorTL", pdp.getCurrent(3) + Math.random() / 10000);
//		SmartDashboard.putNumber("errorTR", pdp.getCurrent(2) + Math.random() / 10000);
//		SmartDashboard.putNumber("errorBL", pdp.getCurrent(13) + Math.random() / 10000);
//		SmartDashboard.putNumber("errorBR", pdp.getCurrent(12) + Math.random() / 10000);

//		SmartDashboard.putNumber("errorTL", driveTrain.topLeftWheel.error + Math.random() / 10000);
//		SmartDashboard.putNumber("errorTR", driveTrain.topRightWheel.error + Math.random() / 10000);
//		SmartDashboard.putNumber("errorBL", driveTrain.bottomLeftWheel.error + Math.random() / 10000);
//		SmartDashboard.putNumber("errorBR", driveTrain.bottomRightWheel.error + Math.random() / 10000);
//		
//		WheelSpeeds rates = driveTrain.getEncoderRates();
//		SmartDashboard.putNumber("encTL", Math.abs(rates.topLeft) + Math.random() / 10000);
//		SmartDashboard.putNumber("encTR", Math.abs(rates.topRight) + Math.random() / 10000);
//		SmartDashboard.putNumber("encBL", Math.abs(rates.bottomLeft) + Math.random() / 10000);
//		SmartDashboard.putNumber("encBR", Math.abs(rates.bottomRight) + Math.random() / 10000);
		
	}

	@Override
	public void testPeriodic() {
		if (Robot.secondStick.getRawButton(1)) {
			Robot.winch.lower();
		} else {
			Robot.winch.stop();
		}
	}
}
