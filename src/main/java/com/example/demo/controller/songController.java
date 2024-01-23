package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.entities.Song;
import com.example.demo.services.songService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class songController {
	@Autowired
	songService service;
	@PostMapping("/addSong")
	public String  addSong(@ModelAttribute Song song) {
		boolean songStatus=service.songExists(song.getName());
		if(songStatus==false) {
			service.addSong(song);
			System.out.println("song added successfully");
		}
		else
		{
			System.out.println("song already exists");
		}
		return "adminHome";
	}

	@GetMapping("/viewSongs")
	public String viewSongs(Model model) {
		List<Song> songList=service.fetchAllSongs();
		
		model.addAttribute("songs",songList);
		System.out.println(songList);
		return "displaySongs";

	}
	@GetMapping("/playSongs")
	public String playSongs(Model model) {
		boolean premium_user=true;
		if(premium_user==true) {
		List<Song> songList=service.fetchAllSongs();
		
		model.addAttribute("songs",songList);
		System.out.println(songList);
		return "displaySongs";
	}
		else
		{
			return "makePayment";
		}
	}




}

