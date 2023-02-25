package com.example.oauth2server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
	@GetMapping("/user")
	public String getUser() {
		return "this is user detail";
	}
	
	@GetMapping("/myCard")
	public String getCard() {
		return "this is your card";
	}
	
	@GetMapping("/myBalance")
	public String getBalance() {
		return "this is your balance";
	}
}
