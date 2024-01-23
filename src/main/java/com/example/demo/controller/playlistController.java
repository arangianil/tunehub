package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.services.playlistService;
import com.example.demo.services.songService;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class playlistController {
	@Autowired
	songService service;
	@Autowired
	playlistService service1;
	@GetMapping("/createPlaylist")
	public String createPlaylist(Model model) {
		List<Song> songList=service.fetchAllSongs();
		model.addAttribute("songs",songList);
		return "createPlaylist";
	}
	@PostMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		service1.addPlaylist(playlist);
		List<Song>songList=playlist.getSongs();
		for(Song s:songList) {
			s.getPlaylist().add(playlist);
			service.updateSong(s);
			//update song object in db
		}
		return "adminHome";
	}
	@GetMapping("/viewPlaylists")
	public String viewPlaylists(Model model) {
		List<Playlist> allPlaylists=service1.fetchAllPlaylists();
		model.addAttribute("allPlaylists",allPlaylists);
		
		return "displayPlaylists" ;
	}
	
	

}
