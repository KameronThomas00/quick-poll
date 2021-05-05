package com.apress.quickpoll.controller;

import com.apress.quickpoll.domain.Poll;
import com.apress.quickpoll.domain.Vote;
import com.apress.quickpoll.dto.OptionCount;
import com.apress.quickpoll.dto.VoteResult;
import com.apress.quickpoll.exception.ResourceNotFoundException;
import com.apress.quickpoll.repository.VoteRepository;
import com.apress.quickpoll.service.ComputeService;
import com.apress.quickpoll.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class ComputeResultController {

    @Autowired
    private ComputeService computeService;

    @Autowired
    private PollService pollService;
    public void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Optional<Poll> poll = pollService.getPoll(pollId);
        if (poll.isEmpty()) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }
    }
    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult (@RequestParam Long pollId) {
        verifyPoll(pollId);
        return new ResponseEntity<>(computeService.computeResult(pollId), HttpStatus.OK);
    }
}
