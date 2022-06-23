package com.emsi.bricoleur.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor  
public class Service {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 50,nullable = false,unique=true)	
	private String libelle;
	private String icon;
	@OneToMany(mappedBy = "service")
	private List<Mission> missions = new  ArrayList<Mission>();
}
