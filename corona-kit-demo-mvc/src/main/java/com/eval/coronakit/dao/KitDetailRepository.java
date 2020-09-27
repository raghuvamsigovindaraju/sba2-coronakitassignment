package com.eval.coronakit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eval.coronakit.entity.KitDetail;

@Repository
public interface KitDetailRepository extends JpaRepository<KitDetail, Integer>{
	
	//List<KitDetail> findAllByGroup(int coronaKitId);
	
	@Query("SELECT c FROM KitDetail c WHERE c.coronaKitId IN id")
	List<KitDetail> findAllKitItemsOfAKit(int id);

}
