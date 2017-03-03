package org.team1515.botswana.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.team1515.botswana.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AlignBad extends Command {

	static final double SPEED = 0.09;
	static final String url = "http://10.15.15.4:8080";
	
	State state;
	double angleResponse;

	private enum State {
		PENDING,
		SUCCESS,
		FAILURE;
	}
	
	@Override
	protected void initialize() {
		state = State.PENDING;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
			String res = reader.readLine();
			angleResponse = Double.parseDouble(res);
			state = State.SUCCESS;
			System.out.println("Angle: " + angleResponse);
		} catch (NumberFormatException ex) {
			state = State.FAILURE;
			System.out.println("No tape found.");
		} catch (Exception ex) {
			state = State.FAILURE;
			System.out.println("Server error");
			ex.printStackTrace();
		}
	}
	
	@Override
	protected boolean isFinished() {
		if (state == State.FAILURE) {
			return true;
		}
		if (state == State.PENDING) {
			return false;
		}
		return true;
	}
	
	@Override
	protected void end() {
		Robot.driveTrain.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
