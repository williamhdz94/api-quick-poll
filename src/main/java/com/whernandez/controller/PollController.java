package com.whernandez.controller;

import java.net.URI;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.whernandez.domain.Poll;
import com.whernandez.repository.PollRepository;

@RestController
public class PollController {

	@Inject
	private PollRepository pollRepository;
	
	@GetMapping("/polls")
	public ResponseEntity<Iterable<Poll>> getAllPolls() {
		Iterable<Poll> allPolls = pollRepository.findAll();
		return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("/polls")
	public ResponseEntity<?> createPoll(@RequestBody Poll poll) {
		poll = pollRepository.save(poll);
		
		// Set the location header for newly created resource
		HttpHeaders resHeaders = new HttpHeaders();
		URI newPollUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(poll.getId())
				.toUri();
		resHeaders.setLocation(newPollUri);
		
		return new ResponseEntity<>(null, resHeaders, HttpStatus.CREATED);
	}
	
}
