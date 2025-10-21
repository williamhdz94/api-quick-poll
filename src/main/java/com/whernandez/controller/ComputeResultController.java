package com.whernandez.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RestController;

import com.whernandez.dto.VoteResult;
import com.whernandez.service.VoteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ComputeResultController {
	
	@Inject
	VoteService voteService;
	
	@GetMapping("/computeresult")
	public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
		// Algorithm to count votes
		VoteResult voteResult = voteService.countVote(pollId);
		
		return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
	}
	
	
}
