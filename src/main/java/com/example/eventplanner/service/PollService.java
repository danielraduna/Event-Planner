package com.example.eventplanner.service;

import com.example.eventplanner.model.Poll;

import java.util.List;

public interface PollService {

    Poll createPoll(Poll poll);

    Poll getPollById(Long id);

    List<Poll> getPollsByEventId(Long eventId);

    Poll updatePoll(Poll poll);

    void deletePoll(Long id);

    void voteForOption(Long pollId, int optionIndex);
}
