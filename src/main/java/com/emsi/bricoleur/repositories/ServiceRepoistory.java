package com.emsi.bricoleur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emsi.bricoleur.entities.Service;

public interface ServiceRepoistory extends JpaRepository<Service, Integer> {

}
