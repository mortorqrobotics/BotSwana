package org.team1515.botswana.subsystems;

import org.team1515.botswana.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearHolder extends Subsystem {

	DoubleSolenoid solenoid1;
	DoubleSolenoid solenoid2;

	public GearHolder() {
		solenoid1 = new DoubleSolenoid(RobotMap.PCM, RobotMap.SOLENOID_GEAR_HOLDER_1.first, RobotMap.SOLENOID_GEAR_HOLDER_1.last);
		solenoid2 = new DoubleSolenoid(RobotMap.PCM, RobotMap.SOLENOID_GEAR_HOLDER_2.first, RobotMap.SOLENOID_GEAR_HOLDER_2.last);
	}

	public void open() {
		solenoid1.set(Value.kForward);
		solenoid2.set(Value.kForward);
	}

	public void close() {
		solenoid1.set(Value.kReverse);
		solenoid2.set(Value.kReverse);
	}

	@Override
	protected void initDefaultCommand() {

	}

}
