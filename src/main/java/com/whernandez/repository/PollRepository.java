package com.whernandez.repository;

import org.springframework.data.repository.CrudRepository;

import com.whernandez.domain.Poll;

public interface PollRepository extends CrudRepository<Poll, Long> {
	
}
