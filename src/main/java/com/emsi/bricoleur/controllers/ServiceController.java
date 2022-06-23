package com.emsi.bricoleur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.emsi.bricoleur.entities.Service;
import com.emsi.bricoleur.repositories.ServiceRepoistory;

@Controller @RequestMapping("/services")
public class ServiceController {
	
    @Autowired
	ServiceRepoistory serviceRepo;
	
	@GetMapping()
	String index(Model m )
	{
		List<Service> list=serviceRepo.findAll();
		m.addAttribute("services", list);
		
		return "public/service";
	}
}
