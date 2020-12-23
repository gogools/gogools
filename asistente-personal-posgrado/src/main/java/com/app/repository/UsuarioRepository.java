package com.app.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.app.domain.Usuario;

import reactor.core.publisher.Flux;

public interface UsuarioRepository extends ReactiveCrudRepository<Usuario, String> {

	Flux<Usuario> findByEmail( String email );
}
