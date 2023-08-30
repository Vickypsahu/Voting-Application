package com.vicky.service;

import java.util.List;

import com.vicky.model.Candidate;
import com.vicky.model.User;
import com.vicky.model.Vote;

public interface UserService {
	//User Service
	public User getUserByUsername(String username);
	public User insertData(User user);
	public User updateUser(User user);
	
	//Candidate Service
	public Candidate getCandidateByName(String candidateName);
	public Candidate saveCandidate(Candidate candidate);
	public List<Candidate> getAllCandidates();

	
	//Vote Service
	public void saveVote(Vote newVote);
	public long getVoteCountForCandidate(Candidate candidate);
	public Vote getUser(User user);


	
}
