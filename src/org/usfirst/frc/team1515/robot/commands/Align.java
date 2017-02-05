package org.usfirst.frc.team1515.robot.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.usfirst.frc.team1515.robot.Robot;
import org.usfirst.frc.team1515.robot.util.WheelSpeeds;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Align extends Command {
	
	static String url = "http://10.15.15.4:8080";
	static final double MIN_SPEED = 0.15;
	static final double MIN_ERROR = 0.75;
	static final int ERROR_INCREMENT_FINISH = 2;
	static final double FORWARD_TURN_SPEED = .01;
	static final double FORWARD_SPEED = .1;
	static final int ITERATIONS = 2;
	
	static final double PTURN = 0.01;
	static final double PFORWARD = 0.0001;
	static final double I = 0.00000;
	//static final double D = 0.000001;
	
	State state;
	double targetAngle;
	double gyroStartAngle;
	
	double errorSum;
	double lastError = 0;
	int errorIncrement = 0;
	int iterations = 0;
	double p;
	
    public Align() {
        requires(Robot.driveTrain);
    }
    
    public void sendRequest() {
    	state = State.PENDING;
    	try {
//    		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
//    		String res = reader.readLine();
//    		System.out.println(res);
//    		targetAngle = Double.parseDouble(res);
    		targetAngle = 5;
    		gyroStartAngle = Robot.gyro.getAngle();
    		lastError = 0;
    		state = State.SUCCESS;
    		iterations++;
    	} catch (NumberFormatException ex) {
    		state = State.FAILURE;
    		System.out.println("No tape found.");
    	} catch (Exception ex) {
    		state = State.FAILURE;
    		System.out.println("Server error");
    		ex.printStackTrace();
    	}
    }

    protected void initialize() {
    	p = PTURN;
    	iterations = 0;
    	errorIncrement = 0;
    	sendRequest();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	if (state == State.FAILURE) {
    		return true;
    	}
    	if (state == State.SUCCESS) {
    		double error = targetAngle - (Robot.gyro.getAngle() - gyroStartAngle);
        	double speed = error * p  + errorSum * I;
        	errorSum += error;
        	if (Math.abs(speed) < MIN_SPEED) {
        		speed = Math.signum(speed) * MIN_SPEED;
        	}
//        	Robot.driveTrain.setSpeed(new WheelSpeeds(speed, -speed, speed, -speed));
//        	Robot.driveTrain.setSpeed(new WheelSpeeds(speed, speed, speed, speed));
        	if (Math.abs(error) <= MIN_ERROR && lastError > MIN_ERROR) {
        		errorIncrement++;
        	}
        	lastError = error;
        	if (errorIncrement >= ERROR_INCREMENT_FINISH) {
        		if (iterations < ITERATIONS) {
	        		errorIncrement = 0;
	        		end();
	        		sendRequest();
        		} else if (iterations >= ITERATIONS) {
        			p = PFORWARD;
        		}
        	//	return true;
        	}
    	}
    	return !Robot.limitSwitch.get();
    	//return false;
    }

    protected void end() {
    	Robot.driveTrain.stop();
    }

    protected void interrupted() {
    	state = State.FAILURE;
    	end();
    }
    
    private enum State {
    	PENDING,
    	SUCCESS,
    	FAILURE;
    }
    
}
