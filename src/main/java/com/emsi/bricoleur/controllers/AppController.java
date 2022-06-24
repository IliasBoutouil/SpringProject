package com.emsi.bricoleur.controllers;


import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emsi.bricoleur.entities.Client;
import com.emsi.bricoleur.entities.Mission;
import com.emsi.bricoleur.entities.User;
import com.emsi.bricoleur.repositories.MissionRepository;
import com.emsi.bricoleur.repositories.UserRepository;

@Controller @RequestMapping("/")
public class AppController {
 
	@Autowired
	UserRepository userRepo;
	@Autowired
	MissionRepository missionRepo; 
	@GetMapping()
	String index(Model m )
	{
		List<Mission> list=missionRepo.findTop4ByOrderByCreatedOnDesc();
		
		m.addAttribute("missions", list);
		
		return "public/index";
	}
	
	@GetMapping("/register")
	String register(Principal p)
	{
		if(p!=null)
			return "redirect:/";
		return "public/register";
	}
	@GetMapping("/unauthorized")
	String unauthorized()
	{
		return "public/unauthorized";
	}
	  @PostMapping("/register")
	  public String store(@Valid Client user,BindingResult br)
		{
		  System.out.println(user);
		  System.out.println(br);
		  PasswordEncoder encoder = new BCryptPasswordEncoder();
		  if (!br.hasErrors()) {
			    user.setPassword(encoder.encode(user.getPassword()));
			    user.setActive(true);
			    userRepo.save(user);
				return "redirect:/missions";
			}
			else {
				return "public/register";
			}
				
		}
}
