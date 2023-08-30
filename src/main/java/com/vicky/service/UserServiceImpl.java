package com.vicky.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicky.model.Candidate;
import com.vicky.model.User;
import com.vicky.model.Vote;
import com.vicky.repository.CandidateRepository;
import com.vicky.repository.UserRepository;
import com.vicky.repository.VoteRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private VoteRepository voteRepository;
	
	//User Service Implementation
	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username) ;
	}

	@Override
	public User insertData(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	
	
	
	//Candidate Service Implementation

	@Override
	public Candidate getCandidateByName(String candidateName) {
		return candidateRepository.findByCandidate(candidateName);
	}

	@Override
	public Candidate saveCandidate(Candidate candidate) {
		return candidateRepository.save(candidate);
	}
	
	@Override
	public List<Candidate> getAllCandidates() {
		return candidateRepository.findAll();
	}


	//Vote Service Implementation
	
	@Override
	public void saveVote(Vote newVote) {
		voteRepository.save(newVote);
	}

	@Override
	public long getVoteCountForCandidate(Candidate candidate) {
		return voteRepository.countByCandidate(candidate);
	}

	@Override
	public Vote getUser(User user) {
		return voteRepository.findByUser(user);
	}

	
	
}
