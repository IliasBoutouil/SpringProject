package com.emsi.bricoleur.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Bricoleur")
@Data @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(callSuper=false)
public class Bricoleur extends User {
@Column(length = 25,nullable = false)
private String specialite;
private boolean isDispo=true;
}
 