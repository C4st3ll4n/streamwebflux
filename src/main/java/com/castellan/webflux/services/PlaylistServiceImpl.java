package com.castellan.webflux.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.castellan.webflux.document.Playlist;
import com.castellan.webflux.repository.PlaylistRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlaylistServiceImpl implements PlaylistService {

	@Autowired
	PlaylistRepository playlistrepository;
	
	@Override
	public Flux<Playlist> findAll() {
		return playlistrepository.findAll();
	}

	@Override
	public Mono<Playlist> findById(String id) { 
		return playlistrepository.findById(id);
	}

	@Override
	public Mono<Playlist> save(Playlist playlist) {
		return playlistrepository.save(playlist);
	}

}
