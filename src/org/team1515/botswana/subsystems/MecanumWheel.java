package org.team1515.botswana.subsystems;

import org.team1515.botswana.util.DistancePIDController;
import org.team1515.botswana.util.MotorModule;
import org.team1515.botswana.util.Pair;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MecanumWheel {

	public final MotorModule motor;

//	static final double K_P = 0.0003 * 0.2;
//	static final double K_I = K_P * 2.0 / 1.0;
//	static final double K_D = K_P * 1.0 / 3.0;
	static final double K_P = 0.00001;
	static final double K_I = 0;//K_P * 2.0 / 1.0;
	static final double K_D = 0;//K_P * 1.0 / 3.0;
	static final double MAX_ENCODER_RATE = 5000;

	public double errorSum = 0;
	public double lastError = 0;
	public double lastSpeed = 0;

	public static final double ACCELERATION_LIMIT = 0.05;

	public Encoder encoder;
	private DistancePIDController distancePIDController;

	double lastEncoderPosition = 0;
	
	double firstport;
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
		
		distancePIDController = new DistancePIDController(encoder);
		
		resetPID();
	}

	public void setSpeed(double speed) {
//		if (firstport != 31) {
//			motor.setSpeed(speed * 0.95);
//			return;
//		}
		if (true) { motor.setSpeed(limitAccel(speed)); return; }
		if (speed == 0) {
			motor.setSpeed(0);
			return;
		}
		speed *= MAX_ENCODER_RATE;
		double error = speed + encoder.getRate();
		SmartDashboard.putNumber("error", error);
		errorSum += error;
		double output = K_P * error + K_I * errorSum + K_D * (error - lastError);
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

	private double limitAccel(double speed) {
		double difference = speed - lastSpeed;
		double sign = Math.signum(difference);
		if (Math.abs(speed) > Math.abs(lastSpeed) && Math.abs(difference) > ACCELERATION_LIMIT) {
			speed = lastSpeed + ACCELERATION_LIMIT * sign;
		}
		lastSpeed = speed;
		return speed;
	}
	
	public void itializeDistancePID(double distanceTarget) {
		distancePIDController.initialize(distanceTarget);
	}
	
	public boolean isOnDistanceTarget() {
		return distancePIDController.onTarget();
	}
	
	public double getDistanceError() {
		return distancePIDController.get();
	}
}
