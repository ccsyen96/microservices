package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.error.ControllerException;
import com.app.model.CovidCasesArea;
import com.app.model.CovidCasesDesc;
import com.app.service.covid.CovidService;
import com.app.service.covid.api.CovidMiningAPITotalCases;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CovidController {

	private static final String GET_LATEST_COVID_FROM_DB = "/covid/get/latest";

	private static final String GET_COVID = "/covid/get";

	private static final String GET_COVID_DESC = "/covid/get/desc";

	private static final String ADD_COVID = "/covid/add";

	private static final String DELETE_COVID = "/covid/delete";

	private static final String GET_HELLO_API = "/covid/hello";

	private static final String GET_LOG_API = "/covid/logging";
	
	private static final String PUT_API = "/covid/put";
	
	private static final String POST_API = "/covid/post";
	
	private static final String DELETE_COVID_SOAPUI = "/covid/delete/soap";

	private static final String FIND_DUPLICATE_DELETE_COVID = "/covid/delete/duplicate";

	@Autowired
	private CovidService covidService;

	@Autowired
	CovidMiningAPITotalCases covidMiningAPITotalCases;

	@GetMapping(GET_LATEST_COVID_FROM_DB)
	public String getLatest() throws ControllerException {
		log.info("getLatest() started");
		String returnString = null;

		try {
			returnString = covidMiningAPITotalCases.getTotalfromDB();
		} catch (Exception e) {
			// Auto-generated catch block
			log.error(" getLatest() exception " + e.getMessage());
			throw new com.app.error.ControllerException(GET_LATEST_COVID_FROM_DB, e.getMessage());
		}

		log.info(GET_LATEST_COVID_FROM_DB + "  return = {}" + returnString);
		return returnString;
	}

	@GetMapping(GET_COVID_DESC)
	public List<CovidCasesDesc> findAllDesc() throws ControllerException {
		log.info("findAll() started");
		List<CovidCasesDesc> covidCasesdescs = null;
		try {
			covidCasesdescs = covidService.getCovidDesc();
		} catch (Exception e) {
			// Auto-generated catch block
			log.error(" findAll() exception " + e.getMessage());
			throw new com.app.error.ControllerException(GET_COVID_DESC, e.getMessage());
		}

		log.info(GET_COVID_DESC + "  return = {}" + covidCasesdescs);
		return covidCasesdescs;
	}

	@GetMapping(GET_COVID)
	public List<CovidCasesArea> findAll() throws ControllerException {
		log.info("findAll() started");
		List<CovidCasesArea> covidCasesAreas = null;
		try {
			covidCasesAreas = covidService.getCovid();
		} catch (Exception e) {
			// Auto-generated catch block
			log.error(" findAll() exception " + e.getMessage());
			throw new com.app.error.ControllerException(GET_COVID, e.getMessage());
		}

		log.info(GET_COVID + "  return = {}" + covidCasesAreas);
		return covidCasesAreas;
	}

	// Practical 1 - Complete the API below
	// It should return hello when you hit http://localhost:8081/covid/hello on
	// browser

	@GetMapping(GET_HELLO_API)
	public String getHello() throws ControllerException {
		log.info("getHello() started");

		return "Hello API";
	}

	// Practical 2 - Capture the error message below from log file
	// It should return some error when you pass a string as parameter to the HTTP
	// get
	// Example, http://localhost:8081/covid/hello?aNumberOnly=string

	@GetMapping(GET_LOG_API)
	public String getLogging(@RequestParam String aNumberOnly) throws ControllerException {
		log.info("getLogging() started, requestParamvalue={}", aNumberOnly);
		
		int num=0;
		if (aNumberOnly != null) {
			num=Integer.parseInt(aNumberOnly);
		}
		return "you have input =>" + num;
	}

	// Practical 4 (Add)
	// Move the logic below under try/catch area to CovidServiceImpl
	// check out the remarks of "Practical 4 " on CovidServiceImpl
	@GetMapping(ADD_COVID)
	public CovidCasesDesc addCovid(@RequestParam(required = true) String desc) throws ControllerException {
		log.info("addCovid() started={}", desc);

		CovidCasesDesc covidCasesDesc = null;
		try {

			if (desc == null || desc.equals("undefined") || desc.equals(""))  {
				throw new NullPointerException(ADD_COVID + ", desc is null or empty");
			}
			covidCasesDesc=covidService.addCovid(desc);
		} catch (Exception e) {
			// Auto-generated catch block
			log.error("add() exception " + e.getMessage());
			throw new com.app.error.ControllerException(ADD_COVID, e.getMessage());
		}

		return covidCasesDesc;
	}

	// Practical 4 (Delete)
	// Move the logic below under try/catch area to CovidServiceImpl
	// check out the remarks of "Practical 4 " on CovidServiceImpl
	@DeleteMapping(DELETE_COVID)
	public int deleteCovid(@RequestParam(required = true) long id) throws ControllerException {
		log.info("deleteCovid() started id={}", id);
		
		int covidCasesDesc = 0;
		try {

			if (id == 0)  {
				throw new NullPointerException(DELETE_COVID + ", id is null or empty");
			}
			covidCasesDesc=covidService.deleteCovid(id);
		} catch (Exception e) {
			// Auto-generated catch block
			log.error("delete() exception " + e.getMessage());
			throw new com.app.error.ControllerException(DELETE_COVID, e.getMessage());
		}

		return covidCasesDesc;

	}
	// Angular Practical 7 - Full Stack Application for Covid Put HTTP
	@PutMapping(PUT_API)
	public CovidCasesDesc putCovid(@RequestBody CovidCasesDesc covidCasesDesc) throws ControllerException {
		log.info("putCovid() started, covidCasesDesc={}", covidCasesDesc);
		try {

			if (covidCasesDesc == null)  {
				throw new NullPointerException(PUT_API + ", desc is null or empty");
			}
			covidCasesDesc=covidService.putCovid(covidCasesDesc);
		} catch (Exception e) {
			// Auto-generated catch block
			log.error("putCovid() exception " + e.getMessage());
			throw new com.app.error.ControllerException(PUT_API, e.getMessage());
		}
		
		log.info("putCovid() ends, covidCasesDescSaved={}", covidCasesDesc);
		
		// return should be the Saved CovidCasesDesc with values
		return covidCasesDesc;
	}
	@PostMapping(POST_API)
	public CovidCasesDesc postCovid(@RequestBody CovidCasesDesc covidCasesDesc) throws ControllerException {
		log.info("postCovid() started, covidCasesDesc={}", covidCasesDesc);
		try {

			if (covidCasesDesc == null)  {
				throw new NullPointerException(POST_API + ", desc is null or empty");
			}
			covidCasesDesc=covidService.postCovid(covidCasesDesc);
		} catch (Exception e) {
			// Auto-generated catch block
			log.error("postCovid() exception " + e.getMessage());
			throw new com.app.error.ControllerException(POST_API, e.getMessage());
		}
		
		log.info("postCovid() ends, covidCasesDescSaved={}", covidCasesDesc);
		
		// return should be the Saved CovidCasesDesc with values
		return covidCasesDesc;

	}
	// Performance Practical 2 - Performance and Functional Testing
	@DeleteMapping(DELETE_COVID_SOAPUI)
	public int deleteCovidSoap(@RequestParam(required = true) String desc) throws ControllerException {
		log.info("deleteCovidSoap() started desc={}", desc);
		
		int covidCasesDesc = 0;
		try {

			if (desc == null)  {
				throw new NullPointerException(DELETE_COVID_SOAPUI + ", desc is null or empty");
			}
			covidCasesDesc=covidService.deleteCovidSoap(desc);
		} catch (Exception e) {
			// Auto-generated catch block
			log.error("deleteCovidSoap() exception " + e.getMessage());
			throw new com.app.error.ControllerException(DELETE_COVID_SOAPUI, e.getMessage());
		}

		return covidCasesDesc;

	}
	
	// Angular Practical 11 - Remove Duplicate values
	@DeleteMapping(FIND_DUPLICATE_DELETE_COVID)
	public List<String> findDuplicateNdelete() throws ControllerException {
		
		log.info("findDuplicateNdelete() started");
		List<String> covidCasesAreas = null;
		try {
			covidCasesAreas = covidService.findDuplicateNdelete();
		} catch (Exception e) {
			// Auto-generated catch block
			log.error("findDuplicateNdelete() exception " + e.getMessage());
			throw new com.app.error.ControllerException(FIND_DUPLICATE_DELETE_COVID, e.getMessage());
		}

		log.info(FIND_DUPLICATE_DELETE_COVID + "  return = {}" + covidCasesAreas);
		return covidCasesAreas;
	}
	
}
