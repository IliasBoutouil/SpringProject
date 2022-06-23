package com.emsi.bricoleur.controllers;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emsi.bricoleur.entities.Mission;
import com.emsi.bricoleur.entities.Service;
import com.emsi.bricoleur.repositories.MissionRepository;
import com.emsi.bricoleur.repositories.ServiceRepoistory;

@Controller @RequestMapping("/missions")
public class MissionController {
	@Autowired
	MissionRepository missionRepo;
	@Autowired
	ServiceRepoistory serviceRepo;
	@GetMapping()
	String index(Model m,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "") String titre,@RequestParam(defaultValue = "") String desc,@RequestParam(defaultValue = "") String service)
	{
		Page<Mission> list=missionRepo.Search(titre,desc,service,PageRequest.of(page, 8));
		//Page<Mission> list=missionRepo.findAll(PageRequest.of(page, 8));
		m.addAttribute("listePage", new int[list.getTotalPages()]);
		m.addAttribute("currentPage", page);
		m.addAttribute("title", titre);
		m.addAttribute("desc", desc);
		m.addAttribute("service", service);
		m.addAttribute("missions", list);
		
		return "missions/index";
	}
	  @GetMapping("/{id}")
	  String details(Model m,@PathVariable int id  )
		{
			Mission result=missionRepo.findById(id).orElse(null);
			if(result!=null)
			{
				
				m.addAttribute("mission", result);
				
				return "missions/details";
			}
			return "public/404";
			
		}
	  @GetMapping("/create")
	  String create(Model m )
		{
			List<Service> list=serviceRepo.findAll();
			m.addAttribute("services", list);
			return "missions/addForm";
			
		}
	  @GetMapping("/edit/{id}")
	  String edit(Model m, @PathVariable int id)
		{
		  Mission result=missionRepo.findById(id).orElse(null);
			if(result!=null)
			{
				m.addAttribute("mission", result);
				List<Service> list=serviceRepo.findAll();
				m.addAttribute("services", list);
				
				return "missions/editForm";
			}
			return "public/404";
			
			
			
			
		}
	  
	  @GetMapping("/delete/{id}")
	  String delete(@PathVariable int id)
		{
				  missionRepo.deleteById(id);
				  return "redirect:/missions";	
		}
	  
	  @PostMapping("/store")
	  public String store(Mission mission,BindingResult br)
		{
		  if (!br.hasErrors()) {
			   mission.setCreatedOn(new Date());
				missionRepo.save(mission);
				return "redirect:/missions";
			}
			else {
				return "redirect:/missions/create";
			}
				
		}
}
