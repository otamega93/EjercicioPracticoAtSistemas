package com.atsistemas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.entities.Match;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {

}
