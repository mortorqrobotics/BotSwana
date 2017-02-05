package org.usfirst.frc.team1515.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MecanumWheel {

	public final CANTalon motor;
	public final EncoderWrapper encoder;
	// public final PIDController pid;

	private double p = 0.5;
	private double i = 0.00000;
	private double d = 0.0;

	public double lastSpeed = 0;
	public double errorSum = 0;
	public double lastError = 0;
	public static final double ACCELERATION_LIMIT = 0.1;
//
	public MecanumWheel(int motorPort, int encoderPort1/* , int encoderPort2 */) {
		motor = new CANTalon(motorPort);
		motor.setSafetyEnabled(false); // stops robot from randomly freezing
										// during competition

//		 encoder = new Encoder(encoderPort1, encoderPort2, true,
		// Encoder.EncodingType.k4X);
		encoder = new EncoderWrapper(encoderPort1);
		// encoder.setMaxPeriod(.05);
		// encoder.setMinRate(10);
		// encoder.setDistancePerPulse(1);
		// encoder.setSamplesToAverage(10);
		// encoder.reset();
//
	}
//
	public void updatePid() {
		p = SmartDashboard.getNumber("p", p);
		i = SmartDashboard.getNumber("i", i);
	}
//
	public void setSpeed(double speed) {
//		System.out.println(encoder.getPosition());
//		double error = speed - encoder.getSpeed();
//		double accel = p * error + i * errorSum + d * (error - lastError) ;
//		speed = lastSpeed + accel;
//		errorSum += error;
//		SmartDashboard.putNumber("speed", speed);
//		speed = limitAccel(speed);
//		SmartDashboard.putNumber("error", error);
		motor.set(speed);
//		lastSpeed = speed;
//		lastError = error;
	}	
//
	private double limitAccel(double speed) {
		double difference = speed - lastSpeed;
		double sign = Math.signum(difference);
		if (Math.abs(difference) > ACCELERATION_LIMIT) {
			speed = lastSpeed + ACCELERATION_LIMIT * sign;
		}
		return speed;
	}
}
