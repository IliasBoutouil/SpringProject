package com.emsi.bricoleur.repositories;





import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.emsi.bricoleur.entities.Mission;

public interface MissionRepository extends JpaRepository<Mission, Integer> {

	List<Mission> findTop4ByOrderByCreatedOnDesc();
	Page<Mission> findByTitreContainsAndDescriptionContainsAndServiceLibelleContainsOrderByCreatedOnDesc(String titre,String desc,String service,Pageable p);
	default Page<Mission> Search(String titre,String desc,String service,Pageable p)
	{
		return findByTitreContainsAndDescriptionContainsAndServiceLibelleContainsOrderByCreatedOnDesc(titre,desc,service,p);
	}
}
