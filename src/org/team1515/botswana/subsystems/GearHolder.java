package org.team1515.botswana.subsystems;

import org.team1515.botswana.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearHolder extends Subsystem {

	DoubleSolenoid solenoid;
	DigitalInput limitSwitch1;
	DigitalInput limitSwitch2;
	boolean isOpen;

	public GearHolder() {
		solenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.SOLENOID_GEAR_HOLDER.first, RobotMap.SOLENOID_GEAR_HOLDER.last);
		limitSwitch1 = new DigitalInput(RobotMap.LIMIT_SWITCH_1);
		limitSwitch2 = new DigitalInput(RobotMap.LIMIT_SWITCH_2);
		isOpen = false;
		close(); // so do not access SmartDashboard from there!!!
	}

	public void open() {
		solenoid.set(Value.kForward);
		isOpen = true;
	}

	public void close() {
		solenoid.set(Value.kReverse);
		isOpen = false;
	}
	
	public void toggle() {
		if (isOpen) {
			close();
		} else {
			open();
		}
	}
	
	public boolean isOpen() {
		return isOpen;
	}
	
	public boolean isGearDetected() {
		return !limitSwitch1.get() || !limitSwitch2.get();
	}

	@Override
	protected void initDefaultCommand() {

	}

}
