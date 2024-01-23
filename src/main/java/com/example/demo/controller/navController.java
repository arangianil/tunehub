package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class navController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/Register")
	public String Register() {
		return "Register";
	}
	@GetMapping("/addSong")
	public String addSong() {
		return "addSong";
	}
}
