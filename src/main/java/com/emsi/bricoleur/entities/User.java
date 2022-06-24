package com.emsi.bricoleur.entities;
import java.util.Date;


import javax.persistence.*;

import javax.validation.constraints.Email;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE")
@Entity @Table(name = "utilisateur") @Data @AllArgsConstructor @NoArgsConstructor 
public abstract class User {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column(length = 25,nullable = false)
private String nom;
@Column(length = 25,nullable = false)
private String prenom;
@Column(length = 250,nullable = false)
private String adresse;
@Temporal(TemporalType.DATE) 
@DateTimeFormat(pattern = "yyyy-MM-dd")
private Date date_naissance;
@Column(unique = true,nullable = false)
@Email
private String email;
@Column(nullable = false)
private String password;
@Column(nullable = false)
private String phone;
@Column(name = "created_on")
@CreationTimestamp
private Date createdOn;
private boolean active=false;


}
