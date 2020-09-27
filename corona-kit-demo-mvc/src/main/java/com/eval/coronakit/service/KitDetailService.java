package com.eval.coronakit.service;

import java.util.List;

import com.eval.coronakit.entity.KitDetail;
import com.eval.coronakit.exception.KitDetailsException;

public interface KitDetailService {
	public KitDetail addKitItem(KitDetail kitItem) throws KitDetailsException;
	public KitDetail getKitItemById(int itemId) throws KitDetailsException;
	public List<KitDetail> getAllKitItemsOfAKit(int kitId) throws KitDetailsException;
}
