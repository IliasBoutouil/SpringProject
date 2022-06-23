package com.emsi.bricoleur.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emsi.bricoleur.entities.Mission;
import com.emsi.bricoleur.repositories.MissionRepository;

@Controller @RequestMapping("/")
public class AppController {
 
	@Autowired
	MissionRepository missionRepo; 
	@GetMapping()
	String index(Model m )
	{
		List<Mission> list=missionRepo.findTop4ByOrderByCreatedOn();
		
		m.addAttribute("missions", list);
		
		return "public/index";
	}
	
}
