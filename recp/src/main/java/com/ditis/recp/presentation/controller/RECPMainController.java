package com.ditis.recp.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RECPMainController{

	@GetMapping("/")
	public String index() {
		return "Greetings from the RECP!";
	}

}