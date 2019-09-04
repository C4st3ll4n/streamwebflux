package com.castellan.webflux.document;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;

import com.castellan.webflux.repository.PlaylistRepository;

import reactor.core.publisher.Flux;

public class DummyData implements CommandLineRunner {

	private final PlaylistRepository playlistRepository;
	
	public DummyData(PlaylistRepository playlistr) {
		this.playlistRepository = playlistr;
	}

	@Override
	public void run(String... args) throws Exception {
		playlistRepository.deleteAll()
		.thenMany(
				Flux.just("PHP","Java","Python", "R", "Git", "Shiny","Android", "Ionic", "JS", "Tensorflow",
						"Keras","Pytorch", "PowerBI", "CodeIgniter","Laravel","Spring","Django","Flask")
				.map(nome -> new Playlist(UUID.randomUUID().toString(), nome))
				.flatMap(playlistRepository::save))
		.subscribe(System.out::println);
			
		
	}
	
	
}
