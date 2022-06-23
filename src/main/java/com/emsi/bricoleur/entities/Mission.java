package com.emsi.bricoleur.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor  
public class Mission  {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 50,nullable = false)	
	private String titre;
	@Column(length = 500,nullable = false)	
	String description; 
	@CreationTimestamp 
	private Date createdOn;
	@Column(nullable = false)	
	@Temporal(TemporalType.DATE) 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateDebut;
	@Column(nullable = false)	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE) 
	private Date dateFin;
	@Column(nullable = false)	
	private int jours;
	@Column(nullable = false)
	private Double budget;
	private boolean isConfirmed=false;
	@ManyToOne
	private Client propriotaire;
	@ManyToOne 
	private Service service;
	
}
