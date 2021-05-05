package com.apress.quickpoll.service;

import com.apress.quickpoll.domain.Poll;
import com.apress.quickpoll.domain.Vote;
import com.apress.quickpoll.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoteService {

    @Autowired
    VoteRepository voteRepository;
    public void createVote (Long pollId, Vote vote) {
        voteRepository.save(vote);
    }
    public void deleteVote(Long voteId){
        voteRepository.deleteById(voteId);
    }
    public Iterable<Vote> getAllVotes(Long pollId) {
        return voteRepository.findByPoll(pollId);
    }


}
