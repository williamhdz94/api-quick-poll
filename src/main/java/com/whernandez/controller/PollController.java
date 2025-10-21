package com.whernandez.controller;

import java.net.URI;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/polls/{pollId}")
	public ResponseEntity<?> getPoll(@PathVariable Long pollId) throws Exception {
		Optional<Poll> pollOptional = pollRepository.findById(pollId);
		if ( !pollOptional.isPresent() ) {
			throw new Exception("Poll not found");
		}
		
		return new ResponseEntity<>(pollOptional.get(), HttpStatus.OK);
	}
	
	@PutMapping("/polls/{pollId}")
	public ResponseEntity<?> updatePollEntity(@RequestBody Poll poll, @PathVariable Long pollId) {
		// Save the entity
		Poll newPoll = pollRepository.save(poll);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/polls/{pollId}")
	public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
		pollRepository.deleteById(pollId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
