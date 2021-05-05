package com.apress.quickpoll.service;

import com.apress.quickpoll.domain.Vote;
import com.apress.quickpoll.dto.OptionCount;
import com.apress.quickpoll.dto.VoteResult;
import com.apress.quickpoll.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ComputeService {

    @Autowired
    VoteRepository voteRepository;
    public VoteResult computeResult (Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
        //algorithm to count votes
        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<>();
        for (Vote v : allVotes) {
            totalVotes ++;
            OptionCount optionCount = tempMap.get(v.getOption().getId());
            if (optionCount == null) {
                optionCount = new OptionCount();
                optionCount.setOptionId(v.getOption().getId());
                tempMap.put(v.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount() + 1);
            voteResult.setTotalValues(totalVotes);
            voteResult.settotalVotes(totalVotes);
            voteResult.setResults(tempMap.values());
        }
        return voteResult;
    }





}
