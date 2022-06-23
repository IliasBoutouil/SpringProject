package com.emsi.bricoleur.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Client")
@Data @EqualsAndHashCode(callSuper=false) @AllArgsConstructor @NoArgsConstructor
public class Client extends User {
	@OneToMany(mappedBy = "propriotaire")
	private List<Mission> missions = new  ArrayList<Mission>();
}
