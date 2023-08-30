package com.vicky.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vicky.model.Candidate;
import com.vicky.model.User;
import com.vicky.repository.UserRepository;
import com.vicky.service.UserService;
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	
	@Autowired 
	private UserService userService;
	
	@GetMapping("/result")
	public String showResults(Model model) {
	    List<Candidate> candidates = userService.getAllCandidates();

	    Map<Candidate, Long> candidateVoteCounts = new HashMap<>();
	    for (Candidate candidate : candidates) {
	    	System.out.println("can:"+candidate);
	        long voteCount = userService.getVoteCountForCandidate(candidate);
	        candidateVoteCounts.put(candidate, voteCount);
	    }

	    model.addAttribute("candidateVoteCounts", candidateVoteCounts);
	    return "adminhome"; 
	}

}
