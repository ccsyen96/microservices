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
import com.app.model.CovidCasesBonus;
import com.app.service.covid.CovidBonusService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CovidBonusController {

	private static final String GET_MY_BONUS = "/covid/get/bonus";
	
	private static final String ADD_BONUS = "/bonus/add";

	private static final String DELETE_BONUS = "/bonus/delete";
	
	private static final String PUT_BONUS = "/bonus/put";
	
	private static final String POST_BONUS = "/bonus/post";
	
	private static final String DELETE_BONUS_SOAPUI = "/bonus/delete/soap";

	private static final String FIND_DUPLICATE_DELETE_COVID = "/bonus/delete/duplicate";

	@Autowired
	private CovidBonusService covidBonusService;

	// Practical Bonus Desc Final
	// Objective: to create a set of spring and hibernate services to retrieve data from a new table call "trx_covid_cases_bonus"
	
	// 1. Complete the CovidCasesBonusEntity.java and auto generate a table on DB
	// Enable the line below from application.properties to create new bonus table
	// # spring.jpa.hibernate.ddl-auto=update
	// Then restart application and table being created on the log
	// CREATE TABLE / PRIMARY KEY will create implicit index "trx_covid_cases_bonus_pkey" for table "trx_covid_cases_bonus"
	
	// 2. Insert the dummy data into trx_covid_cases_bonus using PGAdmin
	
	// 3. Complete the method below to return list of CovidCasesBonus from table trx_covid_cases_bonus
	// Files to be modified as below
	
	// CovidCasesBonus - Java POJO 
	// CovidCasesBonusEntity - DB Entity File
	// CovidAreaBonusMapper - Mapper from Java Entity file above to POJO 
	// CovidCasesBonusRepository - Spring JPA Repository or library to query DB. i.e. FindAll() method
	// CovidBonusService - Interface for the service below
	// CovidBonusServiceImpl - Implementation of the service between controller and repo
	
	
	@GetMapping(GET_MY_BONUS)
	public List<CovidCasesBonus> bonus() throws ControllerException {
		List<CovidCasesBonus> covidCasesBonus = null;
		log.info("bonus() started");

		try {
			covidCasesBonus=covidBonusService.bonus();
			if (covidCasesBonus == null) {
				throw new com.app.error.GenericException("No bonus yet");
			}
		} catch (Exception e) {
			// Auto-generated catch block
			log.error("bonus() exception " + e.getMessage());
			throw new com.app.error.ControllerException(GET_MY_BONUS, e.getMessage());
		}

		log.info(GET_MY_BONUS + " return = {}" + covidCasesBonus);
		return covidCasesBonus;
	}
	
	@GetMapping(ADD_BONUS)
	public CovidCasesBonus addBonus(@RequestParam(required = true) String bonus) throws ControllerException {
		log.info("addCovid() started={}", bonus);

		CovidCasesBonus covidCasesBonus = null;
		try {

			if (bonus == null || bonus.equals("undefined") || bonus.equals(""))  {
				throw new NullPointerException(ADD_BONUS + ", bonus is null or empty");
			}
			covidCasesBonus=covidBonusService.addBonus(bonus);
		} catch (Exception e) {
			// Auto-generated catch block
			log.error("add() exception " + e.getMessage());
			throw new com.app.error.ControllerException(ADD_BONUS, e.getMessage());
		}

		return covidCasesBonus;
	}

	@DeleteMapping(DELETE_BONUS)
	public int deleteBonus(@RequestParam(required = true) long id) throws ControllerException {
		log.info("deleteBonus() started id={}", id);

		int covidCasesDesc = 0;
		try {

			if (id == 0)  {
				throw new NullPointerException(DELETE_BONUS + ", id is null or empty");
			}
			covidCasesDesc=covidBonusService.deleteBonus(id);
		} catch (Exception e) {
			// Auto-generated catch block
			log.error("deleteBonus() exception " + e.getMessage());
			throw new com.app.error.ControllerException(DELETE_BONUS, e.getMessage());
		}

		return covidCasesDesc;

	}
	
	@PutMapping(PUT_BONUS)
	public CovidCasesBonus putBonus(@RequestBody CovidCasesBonus covidCasesBonus) throws ControllerException {
		
		try {

			if (covidCasesBonus == null)  {
				throw new NullPointerException(PUT_BONUS + ", bonus is null or empty");
			}
			covidCasesBonus=covidBonusService.putBonus(covidCasesBonus);
		} catch (Exception e) {
			// Auto-generated catch block
			log.error("putBonus() exception " + e.getMessage());
			throw new com.app.error.ControllerException(PUT_BONUS, e.getMessage());
		}
		
		log.info("putBonus() ends, covidCasesBonusSaved={}", covidCasesBonus);
		
		// return should be the Saved CovidCasesDesc with values
		return covidCasesBonus;

	}
	
	@PostMapping(POST_BONUS)
	public CovidCasesBonus postCovid(@RequestBody CovidCasesBonus covidCasesBonus) throws ControllerException {
		log.info("postBonus() started, covidCasesBonus={}", covidCasesBonus);

		try {

			if (covidCasesBonus == null)  {
				throw new NullPointerException(POST_BONUS + ", bonus is null or empty");
			}
			covidCasesBonus=covidBonusService.postBonus(covidCasesBonus);
		} catch (Exception e) {
			// Auto-generated catch block
			log.error("postCovid() exception " + e.getMessage());
			throw new com.app.error.ControllerException(POST_BONUS, e.getMessage());
		}
		
		log.info("postCovid() ends, covidCasesBonusSaved={}", covidCasesBonus);
		
		// return should be the Saved CovidCasesDesc with values
		return covidCasesBonus;

	}
	
	@DeleteMapping(DELETE_BONUS_SOAPUI)
	public int deleteBonusSoap(@RequestParam(required = true) String bonus) throws ControllerException {
		log.info("deleteBonusSoap() started desc={}", bonus);
		
		int covidCasesBonus = 0;
		try {

			if (bonus == null)  {
				throw new NullPointerException(DELETE_BONUS_SOAPUI + ", bonus is null or empty");
			}
			covidCasesBonus=covidBonusService.deleteBonusDesc(bonus);
		} catch (Exception e) {
			// Auto-generated catch block
			log.error("deleteCovidSoap() exception " + e.getMessage());
			throw new com.app.error.ControllerException(DELETE_BONUS_SOAPUI, e.getMessage());
		}

		return covidCasesBonus;

	}
	
	// Angular Practical 11 - Remove Duplicate values
	@DeleteMapping(FIND_DUPLICATE_DELETE_COVID)
	public List<String> findDuplicateNdelete() throws ControllerException {
		log.info("findDuplicateNdelete() started");
		
		List<String> covidCasesAreas = null;
		try {
			covidCasesAreas = covidBonusService.findDuplicateNdelete();
		} catch (Exception e) {
			// Auto-generated catch block
			log.error("findDuplicateNdelete() exception " + e.getMessage());
			throw new com.app.error.ControllerException(FIND_DUPLICATE_DELETE_COVID, e.getMessage());
		}

		log.info(FIND_DUPLICATE_DELETE_COVID + "  return = {}" + covidCasesAreas);
		return covidCasesAreas;

	}

}
