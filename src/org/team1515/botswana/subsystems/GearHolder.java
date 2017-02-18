package org.team1515.botswana.subsystems;

import org.team1515.botswana.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearHolder extends Subsystem {

	DoubleSolenoid solenoid;

	public GearHolder() {
		solenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.SOLENOID_GEAR_HOLDER.first, RobotMap.SOLENOID_GEAR_HOLDER.last);
	}

	public void open() {
		solenoid.set(Value.kForward);
	}

	public void close() {
		solenoid.set(Value.kReverse);
	}

	@Override
	protected void initDefaultCommand() {

	}

}
