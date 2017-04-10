package org.team1515.botswana.subsystems;

import org.team1515.botswana.util.MotorModule;
import org.team1515.botswana.util.Pair;

import edu.wpi.first.wpilibj.Encoder;

public class MecanumWheel {

	public final MotorModule motor;

//	static final double K_P = 0.0003 * 0.2;
//	static final double K_I = K_P * 2.0 / 1.0;
//	static final double K_D = K_P * 1.0 / 3.0;
	public static double K_P = 0.0001;
	public static double K_I = 0;//K_P * 2.0 / 1.0;
	public static double K_D = 0;//K_P * 1.0 / 3.0;
	static final double MAX_ENCODER_RATE = 5000;

	public double errorSum = 0;
	public double lastError = 0;
	public double lastSpeed = 0;

	public static final double ACCELERATION_LIMIT = 0.05;

	public Encoder encoder;

	double lastEncoderPosition = 0;
	
	int firstport;
	public MecanumWheel(int[] motorPorts, Pair<Integer> encoderPorts) {
		motor = new MotorModule(motorPorts);
		firstport = motorPorts[0];

		encoder = new Encoder(encoderPorts.first, encoderPorts.last, true, Encoder.EncodingType.k4X);
		encoder.setMaxPeriod(.05);
		encoder.setMinRate(10);
		encoder.setDistancePerPulse(1);
		encoder.setSamplesToAverage(10);
//		encoder.setReverseDirection(true);
		encoder.reset();
		
		resetPID();
	}

	public double error;
	public void setSpeed(double speed) {
		double trim = MecanumDrive.trims[firstport - 31];
		speed *= MAX_ENCODER_RATE;
		error = speed + encoder.getRate();
		errorSum += error;
		double output = trim * speed / MAX_ENCODER_RATE + K_P * error + K_I * errorSum + K_D * (error - lastError);
		if (1 <= output || output <= -1) {
			errorSum -= error;
		}
		motor.setSpeed(output);
//		motor.setSpeed(limitAccel(output));
		lastError = error;
	}
	
	public void resetPID() {
		errorSum = 0;
		lastError = 0;
		lastSpeed = 0;
	}

	public void setPidError(double error) {
		errorSum += error;
		double output = K_P * error + K_I * errorSum + K_D * (error - lastError);
		if (1 <= output || output <= -1) {
			errorSum -= error;
		}
		motor.setSpeed(output);
//		motor.setSpeed(limitAccel(output));
		lastError = error;
	}
	
	public double getEncoderRate() {
		return encoder.getRate();
	}

	public int getEncoderDistance() {
		return encoder.get();
	}
	
	public void setBrakeMode(boolean brake) {
		motor.setBrakeMode(brake);
	}

//	private double limitAccel(double speed) {
//		double difference = speed - lastSpeed;
//		double sign = Math.signum(difference);
//		if (Math.abs(speed) > Math.abs(lastSpeed) && Math.abs(difference) > ACCELERATION_LIMIT) {
//			speed = lastSpeed + ACCELERATION_LIMIT * sign;
//		}
//		lastSpeed = speed;
//		return speed;
//	}
	
}
