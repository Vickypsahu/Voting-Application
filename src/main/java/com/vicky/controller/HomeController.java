package com.vicky.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vicky.model.Candidate;
import com.vicky.model.User;
import com.vicky.model.Vote;
import com.vicky.service.UserService;



@Controller
@RequestMapping("/test")
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/home")
	public String homePage(Principal principal,Model m)
	{
	    String username=principal.getName();
	    User user=userService.getUserByUsername(username);
	    boolean vote=user.isHas_vote();
	    System.out.println(vote);
	    if(vote)
	    {
	    	Vote v = userService.getUser(user);
	    	m.addAttribute("hide", false);   	
	    	m.addAttribute("alert", "Already voted to "+v.getCandidate().getName());
	    	return "home";
	    }
	    else
	    {
	    	m.addAttribute("hide", true);
		    return "home";
	    }
	    
		
	}
	
	@PostMapping("/votedata")
	public String voteData(@RequestParam("candidateName")String candidateName,Principal principal,Model model)
	{
		String username=principal.getName();
		User user=userService.getUserByUsername(username);
		if(!user.isHas_vote())
		{
			user.setHas_vote(true);
			userService.updateUser(user);
			
			 // Check if the candidate exists in the Candidate table
	        Candidate candidate = userService.getCandidateByName(candidateName);
	        if (candidate == null) {
	            // Create and save a new Candidate entity
	            candidate = new Candidate();
	            candidate.setName(candidateName);
	            candidate = userService.saveCandidate(candidate);
	        }
			
			//Insert into vote table
			Vote newVote = new Vote();
			newVote.setCandidate(candidate);
		    newVote.setUser(user);
		    userService.saveVote(newVote);
			model.addAttribute("msgs", username+" Voted Successfully");
		}
		else
		{
			Vote vote = userService.getUser(user);
			model.addAttribute("msg", "Already Voted to "+vote.getCandidate().getName());
		}
		model.addAttribute("hide", "true");
		return "home";
	}

}
