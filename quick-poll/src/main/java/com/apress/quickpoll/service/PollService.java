package com.apress.quickpoll.service;

import com.apress.quickpoll.domain.Poll;
import com.apress.quickpoll.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PollService {
    @Autowired
    PollRepository pollRepository;
    public void getAllPolls() {
        Iterable<Poll> allPolls = pollRepository.findAll();
    }
    public void createPoll( Poll poll) {
        pollRepository.save(poll);
    }
    public Optional<Poll> getPoll(Long pollId) {
        return pollRepository.findById(pollId);
    }
    public void updatePoll(Poll poll, Long pollId) {
        // Save the entity
        pollRepository.save(poll);
    }
    public void deletePoll(Long pollId) {
        pollRepository.deleteById(pollId);
    }

}
