package org.team1515.botswana.subsystems;

import org.team1515.botswana.util.MotorModule;
import org.team1515.botswana.util.Pair;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MecanumWheel {

	public final MotorModule motor;

	private double p = 0.5;
	private double i = 0.00000;
	private double d = 0.0;

	public double lastSpeed = 0;
	public double errorSum = 0;
	public double lastError = 0;
	public static final double ACCELERATION_LIMIT = 0.1;

	public MecanumWheel(int[] motorPorts, Pair<Integer> encoderPorts) {
		motor = new MotorModule(motorPorts);

//		encoder = new Encoder(encoderPort1, encoderPort2, true, Encoder.EncodingType.k4X);
//		encoder.setMaxPeriod(.05);
//		encoder.setMinRate(10);
//		encoder.setDistancePerPulse(1);
//		encoder.setSamplesToAverage(10);
//		encoder.reset();

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