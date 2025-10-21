package com.whernandez.dto;

import java.util.Collection;

import lombok.Data;

@Data
public class VoteResult {

	private int totalVotes;
	private Collection<OptionCount> results;
	
}
