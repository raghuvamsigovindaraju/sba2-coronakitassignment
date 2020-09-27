package com.eval.coronakit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.eval.coronakit.dao.CMSUserRepo;
import com.eval.coronakit.entity.CMSUser;

@Component
public class AppStartUpEventHandler {

	@Autowired
	private CMSUserRepo repo;
	
	@Autowired
	private PasswordEncoder penc;
	
	@EventListener
	public void appReady(ApplicationReadyEvent event) {
		if(!repo.existsByUserName("admin")) {
			repo.save(new CMSUser(null, "admin", "admin", penc.encode("admin"),"ADMIN"));
		}

		if(!repo.existsByUserName("First")) {
			repo.save(new CMSUser(null, "First", "abc", penc.encode("abc"),"USER"));
		}
		
		if(!repo.existsByUserName("Second")) {
			repo.save(new CMSUser(null, "Second", "abc", penc.encode("abc"),"USER"));
		}
	
	}
}
