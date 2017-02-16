package org.team1515.botswana.subsystems;

import org.team1515.botswana.util.MotorModule;
import org.team1515.botswana.util.Pair;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MecanumWheel {

	public final MotorModule motor;

	private double p = 0.5;
	private double i = 0.0;
	private double d = 0.0;

	public double lastSpeed = 0;
	public double errorSum = 0;
	public double lastError = 0;
	double lastEncoderPosition = 0;
	double distanceSum = 0;
	double distanceError;

	public static final double ACCELERATION_LIMIT = 0.1;
	public static final int ENCODER_TICKS_PER_REVOLUTION = 2*4*2*4*2*4;
	public static final double WHEEL_CIRCUMFERENCE = 3.14;

	public Encoder encoder;

	public MecanumWheel(int[] motorPorts, Pair<Integer> encoderPorts) {
		motor = new MotorModule(motorPorts);

		encoder = new Encoder(encoderPorts.first, encoderPorts.last, true, Encoder.EncodingType.k4X);
		encoder.setMaxPeriod(.05);
		encoder.setMinRate(10);
		encoder.setDistancePerPulse(1);
		encoder.setSamplesToAverage(10);
		encoder.reset();

	}

	public void updatePid() {
		p = SmartDashboard.getNumber("p", p);
		i = SmartDashboard.getNumber("i", i);
		d = SmartDashboard.getNumber("d", d);
	}

	public void setSpeed(double speed) {
//		System.out.println(encoder.getPosition());
//		double error = speed - encoder.getSpeed();
//		double accel = p * error + i * errorSum + d * (error - lastError) ;
//		speed = lastSpeed + accel;
//		errorSum += error;
//		SmartDashboard.putNumber("speed", speed);
//		speed = limitAccel(speed);
//		SmartDashboard.putNumber("error", error);
		motor.setSpeed(speed);
//		lastSpeed = speed;
//		lastError = error;
	}

	public void goDistance(double distance) {
		int encoderPosition = encoder.get();
		double ticks = (distance * ENCODER_TICKS_PER_REVOLUTION / WHEEL_CIRCUMFERENCE);
		distanceSum += encoderPosition - lastEncoderPosition; 
		distanceError = ticks - distanceSum;
		double speed = p * distanceError;
		motor.setSpeed(speed);
		lastEncoderPosition = encoderPosition;
	}

	public boolean onDistanceTarget(double errorRange) {
		return distanceError < errorRange && distanceError > -errorRange;
	}

	private double limitAccel(double speed) {
		double difference = speed - lastSpeed;
		double sign = Math.signum(difference);
		if (Math.abs(difference) > ACCELERATION_LIMIT) {
			speed = lastSpeed + ACCELERATION_LIMIT * sign;
		}
		return speed;
	}
}
