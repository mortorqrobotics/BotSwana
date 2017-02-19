package org.team1515.botswana.subsystems;

import org.team1515.botswana.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearHolder extends Subsystem {

	DoubleSolenoid solenoid;
	boolean isOpen;

	public GearHolder() {
		solenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.SOLENOID_GEAR_HOLDER.first, RobotMap.SOLENOID_GEAR_HOLDER.last);
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

	@Override
	protected void initDefaultCommand() {

	}

}
