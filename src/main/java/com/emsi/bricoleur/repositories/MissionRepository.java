package com.emsi.bricoleur.repositories;





import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.emsi.bricoleur.entities.Mission;

public interface MissionRepository extends JpaRepository<Mission, Integer> {

	List<Mission> findTop4ByOrderByCreatedOn();
	Page<Mission> findByTitreContainsAndDescriptionContainsAndServiceLibelleContains(String titre,String desc,String service,Pageable p);
	default Page<Mission> Search(String titre,String desc,String service,Pageable p)
	{
		return findByTitreContainsAndDescriptionContainsAndServiceLibelleContains(titre,desc,service,p);
	}
}
