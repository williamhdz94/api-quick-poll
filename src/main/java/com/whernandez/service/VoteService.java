package com.whernandez.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.whernandez.domain.Vote;
import com.whernandez.dto.OptionCount;
import com.whernandez.dto.VoteResult;
import com.whernandez.repository.VoteRepository;

@Service
public class VoteService {
	
	@Inject VoteRepository voteRepository;
	
	public VoteResult countVote(Long pollId) {
		VoteResult voteResult = new VoteResult();
		Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
		
		int totalVotes = 0;
		Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>();
		
		for (Vote vote : allVotes) {
			totalVotes ++;
			OptionCount optionCount = tempMap.get(vote.getOption().getId());
			
			if ( optionCount == null ) {
				optionCount = new OptionCount();
				optionCount.setOptionId(vote.getOption().getId());
				tempMap.put(vote.getId(), optionCount);
			}
			
			optionCount.setCount(optionCount.getCount() + 1);
		}
		voteResult.setTotalVotes(totalVotes);
		voteResult.setResults(tempMap.values());
		
		return voteResult;
	}

}
