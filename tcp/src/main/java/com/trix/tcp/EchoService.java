package com.trix.tcp;

import org.springframework.stereotype.Component;

@Component
public class EchoService {
	
	public String test(String input) {
		if ("FAIL".equals(input)) {
			throw new RuntimeException("Failure Demonstration");
		}
		else if ("q".equals(input)){
			System.exit(0);
		}
		return "echo:" +input;
	}

}