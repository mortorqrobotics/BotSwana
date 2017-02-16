package org.team1515.botswana.util;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PIDOutput;

public class MotorModule implements PIDOutput {

	private CANTalon[] talons;

	public MotorModule(int... motorPorts) {

		talons = new CANTalon[motorPorts.length];
		for (int i = 0; i < motorPorts.length; i++) {
			talons[i] = new CANTalon(motorPorts[i]);
			talons[i].setSafetyEnabled(false); // stop the robot from randomly stopping at competition
		}
	}

	public void setSpeed(double speed){
		speed = Math.max(-1, Math.min(1, speed));
		for (CANTalon talon : talons) {
			talon.set(speed);
		}
	}

	public void stop(){
		setSpeed(0.0);
	}

	public void pidWrite(double output) {
		setSpeed(output);
	}

}
