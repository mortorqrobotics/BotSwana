package org.team1515.botswana.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import edu.wpi.first.wpilibj.command.Command;

public class SendPiRequest extends Command {
	
	static String url = "http://10.15.15.4:8080";
	
	State state;
	Double angleResponse;

    private void sendRequest() {
    	state = State.PENDING;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
			String res = reader.readLine();
			angleResponse = Double.parseDouble(res);
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
    
    public Double getAngleResponse() {
    	if (state == State.SUCCESS) {
    		return angleResponse;
    	} else {
    		return null;
    	}
    }
    
    @Override
    protected void initialize() {
    	sendRequest();
    }

    @Override
    protected boolean isFinished() {
        return state == State.SUCCESS || state == State.FAILURE;
    }

    private enum State {
    	PENDING,
    	SUCCESS,
    	FAILURE;
    }
}
