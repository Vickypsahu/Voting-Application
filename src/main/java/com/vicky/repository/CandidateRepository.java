package com.vicky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vicky.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>{
	
	 @Query("SELECT c FROM Candidate c WHERE c.name = ?1")
	 Candidate findByCandidate(String candidateName);


}
