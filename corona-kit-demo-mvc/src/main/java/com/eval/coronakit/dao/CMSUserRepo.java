package com.eval.coronakit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eval.coronakit.entity.CMSUser;

@Repository
public interface CMSUserRepo extends JpaRepository<CMSUser, Integer>{
	boolean existsByUserName(String userName);
	CMSUser findByUserName(String userName);
}
