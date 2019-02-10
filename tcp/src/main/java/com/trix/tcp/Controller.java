package com.trix.tcp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("/")
	public String getHome() {
		return "home";
	}
}
