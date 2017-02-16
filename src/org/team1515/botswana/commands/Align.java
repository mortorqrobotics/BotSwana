package org.team1515.botswana.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.team1515.botswana.Robot;
import org.team1515.botswana.util.WheelSpeeds;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Align extends Command {
	
	static String url = "http://10.15.15.4:8080";
	static final double MIN_SPEED = 0.15;
	static final double MIN_ERROR = 0.75;
	static final int ERROR_INCREMENT_FINISH = 2;
	static final double FORWARD_SPEED = 0.09;
	static final int ITERATIONS = 2;
	
	static final double P = 0.00000001;
	static final double I = 0;
	static final double D = 0;
	
	State state;
	double targetAngle;
	double gyroStartAngle;
	boolean usingVision;
	
	double errorSum;
	double lastError = 0;
	int errorIncrement = 0;
	int iterations = 0;
	
    public Align() {
        requires(Robot.driveTrain);
        this.usingVision = true;
    }
    
    public Align(double targetAngle) {
    	requires(Robot.driveTrain);
    	this.targetAngle = targetAngle; 
    	this.usingVision = false;
    }
    
    private void sendRequest() {
    	state = State.PENDING;
    	if (usingVision) {
    		try {
    			BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
    			String res = reader.readLine();
    			targetAngle = Double.parseDouble(res);
    			gyroStartAngle = Robot.gyro.getAngle();
    			lastError = 0;
    			state = State.SUCCESS;
    		} catch (NumberFormatException ex) {
    			state = State.FAILURE;
    			System.out.println("No tape found.");
    		} catch (Exception ex) {
    			state = State.FAILURE;
    			System.out.println("Server error");
    			ex.printStackTrace();
    		}
    	}
    }

    protected void initialize() {
    	iterations = 0;
    	errorIncrement = 0;
    	sendRequest();
    	
    	if (!usingVision) {
    		iterations = ITERATIONS - 1;
    	}
    }

    protected boolean isFinished() {
    	if (state == State.FAILURE) {
    		if (iterations < 1) {
    			return true;
    		}
    		Robot.driveTrain.moveForward(FORWARD_SPEED); 
    	}
    	if (state == State.SUCCESS) {
    		System.out.println(targetAngle);
        	if (iterations < ITERATIONS) {
        		double error = targetAngle - (Robot.gyro.getAngle() - gyroStartAngle);
        		double speed = error * P + errorSum * I + (error - lastError) * D;
        		errorSum += error;
        		if (Math.abs(speed) < MIN_SPEED) {
        			speed = Math.signum(speed) * MIN_SPEED;
        		}
        		Robot.driveTrain.setSpeed(new WheelSpeeds(speed, -speed, speed, -speed));
        		if (Math.abs(error) <= MIN_ERROR && lastError > MIN_ERROR) {
        			errorIncrement++;
        		}
        		lastError = error;
        		if (errorIncrement >= ERROR_INCREMENT_FINISH) {
        			iterations++;
        			errorIncrement = 0;
        			end();
        			sendRequest();
        		}
        	} else {
        		Robot.driveTrain.moveForward(FORWARD_SPEED);
        	}

    	}
    	return !Robot.limitSwitch.get();
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
