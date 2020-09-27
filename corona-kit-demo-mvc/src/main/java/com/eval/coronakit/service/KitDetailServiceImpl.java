package com.eval.coronakit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eval.coronakit.dao.KitDetailRepository;
import com.eval.coronakit.entity.KitDetail;
import com.eval.coronakit.exception.KitDetailsException;

@Service
public class KitDetailServiceImpl implements KitDetailService {

	@Autowired
	KitDetailRepository repository;
	
	@Override
	@Transactional
	public KitDetail addKitItem(KitDetail kitItem) throws KitDetailsException
	{
      
		if(kitItem!=null)
		{
			repository.save(kitItem);
		}
		
	    return kitItem;
	}

	@Override
	public KitDetail getKitItemById(int kitId) {
		return repository.findById(kitId).orElse(null);
	}

	@Override
	public List<KitDetail> getAllKitItemsOfAKit(int coronakitId) {
		return repository.findAllKitItemsOfAKit(coronakitId);
	}

}
