package com.example.eventplanner.service.serviceimpl;

import com.example.eventplanner.model.Poll;
import com.example.eventplanner.repository.PollRepository;
import com.example.eventplanner.service.PollService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PollServiceImpl implements PollService {

    private final PollRepository pollRepository;

    @Override
    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    @Override
    public Poll getPollById(Long id) {
        return pollRepository.findById(id).orElse(null);
    }

    @Override
    public List<Poll> getPollsByEventId(Long eventId) {
        return pollRepository.findByEvent_Id(eventId);
    }

    @Override
    public Poll updatePoll(Poll poll) {
        return pollRepository.save(poll);
    }

    @Override
    public void deletePoll(Long id) {
        pollRepository.deleteById(id);
    }

    @Override
    public void voteForOption(Long pollId, int optionIndex) {
        Optional<Poll> pollOpt = pollRepository.findById(pollId);
        if (pollOpt.isPresent()) {
            Poll poll = pollOpt.get();
            List<Integer> votes = poll.getVotes();
            if (optionIndex >= 0 && optionIndex < votes.size()) {
                votes.set(optionIndex, votes.get(optionIndex) + 1);
                pollRepository.save(poll);
            } else {
                throw new IllegalArgumentException("Invalid option index!");
            }
        } else {
            throw new IllegalArgumentException("Poll with given ID doesn't exist!");
        }
    }
}
