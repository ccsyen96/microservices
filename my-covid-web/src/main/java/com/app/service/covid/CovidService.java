package com.app.service.covid;

import java.util.List;

import com.app.model.CovidCasesArea;
import com.app.model.CovidCasesDesc;

public interface CovidService {

	List<CovidCasesArea> getCovid();

	List<CovidCasesDesc> getCovidDesc();

	CovidCasesDesc addCovid(String desc);

	int deleteCovid(long id) throws Exception;

	CovidCasesDesc putCovid(CovidCasesDesc covidCasesDesc) throws RuntimeException;

	CovidCasesDesc postCovid(CovidCasesDesc covidCasesDesc) throws RuntimeException;

	List<CovidCasesDesc> deleteCovidDesc(String desc);

	List<String> findDuplicateNdelete();

}
