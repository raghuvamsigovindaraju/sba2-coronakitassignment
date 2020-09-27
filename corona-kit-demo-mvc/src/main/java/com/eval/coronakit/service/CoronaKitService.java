package com.eval.coronakit.service;

import com.eval.coronakit.entity.CoronaKit;
import com.eval.coronakit.exception.CoronaKitException;

public interface CoronaKitService {
	public CoronaKit saveKit(CoronaKit kit) throws CoronaKitException;
	public CoronaKit getKitById(int kitId)throws CoronaKitException;
}
