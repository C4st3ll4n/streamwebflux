package com.castellan.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.castellan.webflux.document.Playlist;

public interface PlaylistRepository extends ReactiveMongoRepository<Playlist,String>{

}
