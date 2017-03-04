package org.team1515.botswana.util;

import org.team1515.botswana.RobotMap;

import edu.wpi.first.wpilibj.Encoder;

public class DistancePIDController {
	
	static final double ERROR_RANGE = 0.5;
	static final double WHEEL_CIRCUMFERENCE = RobotMap.MECANUM_WHEEL_CIRCUMFERENCE;
	static final double ENCODER_TICKS_PER_REVOLUTION = 10;
	static final double P = 0.05;
	static final double I = 0.0;
	static final double D = 0.0;
	
	Encoder encoder;
	
	int initialEncoderPosition;
	double distanceTarget;
	double error;
	
	public DistancePIDController(Encoder input) {
		this.encoder = input;
	}
	
	public void initialize(double distanceTarget) {
		this.distanceTarget = distanceTarget * ENCODER_TICKS_PER_REVOLUTION / WHEEL_CIRCUMFERENCE; // inches to ticks
		initialEncoderPosition = encoder.get();
	}
	
	public double get() {
		error = (initialEncoderPosition + distanceTarget - encoder.get()) * P;
		return error;
	}
	
	public boolean onTarget() {
		return Math.abs(error) <= ERROR_RANGE;
	}

}
