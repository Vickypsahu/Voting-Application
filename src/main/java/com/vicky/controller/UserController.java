package com.vicky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vicky.model.User;
import com.vicky.service.UserService;


@Controller
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String registerPage()
	{
		return "register";	
	}
	
	@GetMapping("/login")
	public String loginPage()
	{
		return "login";
	}
	
	@PostMapping("/insert")
	public String insertData(@ModelAttribute("user")User user,Model model)
	{
		User u=userService.getUserByUsername(user.getUsername());
		if(u!=null)
		{
			model.addAttribute("msg", "Username already exists!");
			return "register";
		}
		else
		{		
		    user.setPassword(encoder.encode(user.getPassword()));
			user.setRole("ROLE_USER");
			user.setHas_vote(false);
			User us=userService.insertData(user);
			if(us!=null)
			{
				model.addAttribute("msg", "Registered Successfully");
				return "login";
			}
			
				return "register";
					
		}
		
	}
	
	
	
}
