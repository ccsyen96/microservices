package com.app.service.covid;

import java.util.List;

import com.app.model.CovidCasesBonus;

public interface CovidBonusService {
	
	List<CovidCasesBonus> bonus() ;

	CovidCasesBonus putBonus(CovidCasesBonus covidCasesBonus);

	CovidCasesBonus postBonus(CovidCasesBonus covidCasesBonus);

	int deleteBonusDesc(String bonus);

	CovidCasesBonus addBonus(String bonus);

	int deleteBonus(long id);

	List<String> findDuplicateNdelete();

}
