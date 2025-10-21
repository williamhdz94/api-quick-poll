package com.whernandez.repository;

import org.springframework.data.repository.CrudRepository;

import com.whernandez.domain.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long> {

}
