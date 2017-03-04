package org.team1515.botswana.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class SendPiRequest {
static final String url = "http://10.15.15.4:8080";

	State state;
	Double angleResponse;

	private void sendRequest() {
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
		sendRequest();
		if (state == State.SUCCESS) {
			return angleResponse;
		} else {
			return null;
		}
	}

	private enum State {
		SUCCESS,
		FAILURE;
	}
}
