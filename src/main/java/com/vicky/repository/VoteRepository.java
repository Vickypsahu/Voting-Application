package com.vicky.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vicky.model.Candidate;
import com.vicky.model.User;
import com.vicky.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

	long countByCandidate(Candidate candidate);

	Vote findByUser(User user);

}
