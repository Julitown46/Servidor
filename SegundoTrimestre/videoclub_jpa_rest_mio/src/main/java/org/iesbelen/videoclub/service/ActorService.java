package org.iesbelen.videoclub.service;

import org.iesbelen.videoclub.domain.Actor;
import org.iesbelen.videoclub.domain.Categoria;
import org.iesbelen.videoclub.domain.Pelicula;
import org.iesbelen.videoclub.exception.ActorNotFoundException;
import org.iesbelen.videoclub.exception.PeliculaNotFoundException;
import org.iesbelen.videoclub.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    public Actor one(Long id) {
        return this.actorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException(id));
    }

    public Actor save(Actor actor) {
        return actorRepository.save(actor);
    }

    public void delete(Long id) {
        this.actorRepository.findById(id).map(a -> {this.actorRepository.delete(a);
                    return a;})
                .orElseThrow(() -> new ActorNotFoundException(id));
    }

    public Actor replace(Long id, Actor actor) {
        return this.actorRepository.findById(id).map( a -> (id.equals(actor.getIdActor())  ?
                        this.actorRepository.save(actor) : null))
                .orElseThrow(() -> new ActorNotFoundException(id));
    }
}
