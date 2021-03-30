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

import com.app.model.CovidCasesBonus;
import com.app.service.covid.CovidBonusService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CovidBonusController {

	private final static String GET_MY_BONUS = "/covid/get/bonus";
	
	private final static String ADD_BONUS = "/bonus/add";

	private final static String DELETE_BONUS = "/bonus/delete";
	
	private final static String PUT_BONUS = "/bonus/put";
	
	private final static String POST_BONUS = "/bonus/post";
	
	private final static String DELETE_BONUS_SOAPUI = "/bonus/delete/soap";

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
	List<CovidCasesBonus> bonus() throws Exception {
		List<CovidCasesBonus> covidCasesBonus = null;
		log.info("bonus() started");

		try {
			covidCasesBonus=covidBonusService.bonus();
			if (covidCasesBonus == null) {
				throw new Exception("No bonus yet");
			}
		} catch (Exception e) {
			// Auto-generated catch block
			log.error("bonus() exception " + e.getMessage());
			throw new Exception(e);
		}

		log.info(GET_MY_BONUS + " return = {}" + covidCasesBonus);
		return covidCasesBonus;
	}
	
	@GetMapping(ADD_BONUS)
	CovidCasesBonus addBonus(@RequestParam(required = true) String bonus) throws Exception {
		log.info("addCovid() started={}", bonus);

		CovidCasesBonus covidCasesBonus = null;
		try {

			if (bonus == null || bonus.equals("undefined") || bonus.equals(""))  {
				throw new NullPointerException(ADD_BONUS + ", bonus is null or empty");
			}
			covidCasesBonus=covidBonusService.addBonus(bonus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("add() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		return covidCasesBonus;
	}

	@DeleteMapping(DELETE_BONUS)
	int deleteBonus(@RequestParam(required = true) long id) throws Exception {
		log.info("deleteBonus() started id={}", id);

		return covidBonusService.deleteBonus(id);
	}
	
	@PutMapping(PUT_BONUS)
	CovidCasesBonus putBonus(@RequestBody CovidCasesBonus covidCasesBonus) throws Exception {

		return covidBonusService.putBonus(covidCasesBonus);
	}
	
	@PostMapping(POST_BONUS)
	CovidCasesBonus postCovid(@RequestBody CovidCasesBonus covidCasesBonus) throws RuntimeException {
		log.info("postBonus() started, covidCasesBonus={}", covidCasesBonus);

		return covidBonusService.postBonus(covidCasesBonus);
	}
	
	@DeleteMapping(DELETE_BONUS_SOAPUI)

	List<CovidCasesBonus> deleteBonusSoap(@RequestParam(required = true) String bonus) throws Exception {
		log.info("deleteBonusSoap() started desc={}", bonus);

		return covidBonusService.deleteBonusDesc(bonus);
	}

}
