package com.apress.quickpoll.controller;

import com.apress.quickpoll.domain.Poll;
import com.apress.quickpoll.exception.ResourceNotFoundException;
import com.apress.quickpoll.repository.PollRepository;
import com.apress.quickpoll.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class PollController {

    @Autowired
    private PollService pollService;
    @Autowired
    private PollRepository pollRepository;

    public void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Optional<Poll> poll = pollService.getPoll(pollId);
        if (poll.isEmpty()) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }
    }
    @RequestMapping(value = "/polls", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        pollService.getAllPolls();
        return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);
    }
    @RequestMapping(value = "/polls", method = RequestMethod.POST)
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
        pollService.createPoll(poll);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        pollService.getPoll(pollId);
        return new ResponseEntity<>(pollRepository.findById(pollId), HttpStatus.OK);
    }
    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
        pollService.updatePoll(poll, pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        pollService.deletePoll(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}