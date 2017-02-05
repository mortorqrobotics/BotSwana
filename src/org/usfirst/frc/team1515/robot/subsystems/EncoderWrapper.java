package org.usfirst.frc.team1515.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.AnalogTriggerOutput;
import edu.wpi.first.wpilibj.AnalogTriggerOutput.AnalogTriggerType;

public class EncoderWrapper {


	AnalogInput input;
	int lastValue;
	int value;
	double[] pastPositions;
//	AnalogTrigger input;
//	AnalogTriggerOutput rise;
//	AnalogTriggerOutput fall;
//	AnalogTriggerOutput state;
	
	final int MAX_TICKS = 4096;
//	final int MAX_TICKS = 4000;
	final int PAST_POSITION_LENGTH = 10;
	final double SPEED_MULTIPLIER = 1200;
	
	public EncoderWrapper(int port) {
		input = new AnalogInput(port);
//		input.setLimitsVoltage(0, 5);
		lastValue = 0;
		pastPositions = new double[PAST_POSITION_LENGTH];
//		fall = input.createOutput(AnalogTriggerType.kFallingPulse);
//		rise = input.createOutput(AnalogTriggerType.kRisingPulse);
//		state = input.createOutput(AnalogTriggerType.kState);
//		try{
//			fall.requestInterrupts();
//		fall.setUpSourceEdge(false, true);
//		}catch(Exception ex){ex.printStackTrace();}
	}
//	
	public int getPosition() {
		return value;
	}

	int num=0;
	public double getSpeed() {
//		int a=value-num;
//		num=value;
//		return(a);
		return ((pastPositions[0] - pastPositions[pastPositions.length - 1]) / (pastPositions.length - 1))/* / SPEED_MULTIPLIER*/;
	}
//	
	public void update() {
		System.out.println(getSpeed());
		int encoderValue = input.getAverageValue();
		if (Math.abs((Math.abs(lastValue) - encoderValue)) > MAX_TICKS / 2) {
			if (encoderValue < MAX_TICKS / 2 ) {
				value += MAX_TICKS - lastValue + encoderValue;
			} else {
				value += MAX_TICKS - encoderValue + lastValue; 
			}
		} else if (lastValue != encoderValue) {
			value += (encoderValue - lastValue);
		}
		lastValue = encoderValue;
		
		for (int i = pastPositions.length - 1; i > 0; i--) {
			pastPositions[i] = pastPositions[i - 1];
		}
		pastPositions[0] = value;
		
//		for (int i = 0; i < pastPositions.length; i++) {
//			System.out.print(pastPositions[i]);
//			System.out.print(" ");
//		}
//		System.out.println();
//		System.out.println(encerValue + " " + input.getAverageBits());
		
//		System.out.println(getPosition());
	}
	
}