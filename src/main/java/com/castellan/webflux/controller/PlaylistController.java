package com.castellan.webflux.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;


import com.castellan.webflux.document.Playlist;
import com.castellan.webflux.services.PlaylistService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
public class PlaylistController {
	@Autowired
	PlaylistService service;

	@GetMapping(value = "/playlist")
	public Flux<Playlist> getAll() {
		return service.findAll();
	}

	@GetMapping(value = "/playlist/{id}")
	public Mono<Playlist> getPlaylistId(@PathVariable String id) {
		return service.findById(id);
	}
	
	
	@PostMapping(value = "/playlist")
	public Mono<Playlist> save(@RequestBody Playlist playlist){
		return service.save(playlist);
	}
	
	@GetMapping(value="/playlist/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, Playlist>> getPlaylistEvents(){
		Flux<Long> intervalo = Flux.interval(Duration.ofSeconds(15));
		Flux<Playlist> eventos = service.findAll();
		System.out.println("It's already here, My Lord.");
		return Flux.zip(intervalo, eventos);
	}
}
