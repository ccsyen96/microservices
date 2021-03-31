package com.app.service.covid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.entity.CovidCasesBonusEntity;
import com.app.error.IDNotFoundException;
import com.app.mapper.CovidAreaBonusMapper;
import com.app.model.CovidCasesBonus;
import com.app.repository.covid.CovidCasesBonusRepository;

import fr.xebia.extras.selma.Selma;

//TODO: Practical bonus final
//complete this as Dependencies Injection Service
@Service

public class CovidBonusServiceImpl implements CovidBonusService {
	
	private static final org.slf4j.Logger 
	log = org.slf4j.LoggerFactory.getLogger(CovidBonusServiceImpl.class);
	
	@Autowired
	CovidCasesBonusRepository covidCasesBonusRepository;
	// hint
	// the method is similar to getCovidDesc() CovidServiceImpl file
	
	@Override
	public
	List<CovidCasesBonus> bonus() throws Exception {
		log.info("bonus() started");
		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		List<CovidCasesBonusEntity> covidCaseBonusEntities = covidCasesBonusRepository.findAll();
		List<CovidCasesBonus> covidCasesBonusList = new ArrayList<CovidCasesBonus>();
		if (covidCaseBonusEntities == null) {
			throw new IDNotFoundException(0L);
		} else {

			for (CovidCasesBonusEntity entity : covidCaseBonusEntities) {
				CovidCasesBonus model = mapper.asResource(entity);
				covidCasesBonusList.add(model);
				log.info("entity description={}", entity.getDescription());
			}
			log.info(" getCovidBonus() return Size={}", covidCaseBonusEntities.size());
		}
		log.info("bonus() ended-->covidCasesBonusList", covidCasesBonusList);
		return covidCasesBonusList;

	}
	@Override
	public CovidCasesBonus putBonus(@RequestBody CovidCasesBonus covidCasesBonus)throws RuntimeException {
		log.info("putBonus() started, covidCasesBonus={}", covidCasesBonus);

		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		
		CovidCasesBonusEntity covidAreaBonusEntity=mapper.asEntity(covidCasesBonus);
		CovidCasesBonusEntity savedEntity=covidCasesBonusRepository.save(covidAreaBonusEntity);
		
		covidCasesBonus = mapper.asResource(savedEntity);
		
		log.info("putCovid() ends, covidCasesBonusSaved={}", covidCasesBonus);
		
		return covidCasesBonus;
	}
	
	@Override
	public CovidCasesBonus postBonus(@RequestBody CovidCasesBonus covidCasesBonus)throws RuntimeException {
		log.info("postBonus() started, covidCasesBonus={}", covidCasesBonus);

		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		
		CovidCasesBonusEntity covidAreaBonusEntity=mapper.asEntity(covidCasesBonus);
		CovidCasesBonusEntity savedEntity=covidCasesBonusRepository.save(covidAreaBonusEntity);
		
		covidCasesBonus = mapper.asResource(savedEntity);
		
		log.info("postCovid() ends, covidCasesBonusSaved={}", covidCasesBonus);
		
		return covidCasesBonus;
	}
	@Override
	public List<CovidCasesBonus> deleteBonusDesc(String bonus) {
		log.info("deleteCovidDesc started desc={}", bonus);
		covidCasesBonusRepository.deleteBonusWithCondition(bonus);
		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		List<CovidCasesBonusEntity> covidCaseBonusEntities = covidCasesBonusRepository.findAll();
		List<CovidCasesBonus> covidCasesBonusList = new ArrayList<CovidCasesBonus>();
		if (covidCaseBonusEntities == null) {
			throw new IDNotFoundException(0L);
		} else {

			for (CovidCasesBonusEntity entity : covidCaseBonusEntities) {
				CovidCasesBonus model = mapper.asResource(entity);
				covidCasesBonusList.add(model);
				log.info("entity total bonus={}", entity.getDescription());
			}
			log.info(" getCovidBonus() return Size={}", covidCaseBonusEntities.size());
		}

		return covidCasesBonusList;

	}

	@Override
	public CovidCasesBonus addBonus(String bonus) {
		log.info("addCovid started");
		CovidCasesBonus covidCasesBonus = null;
		 
		 CovidCasesBonusEntity covidAreaBonusEntity = new CovidCasesBonusEntity();
		 covidAreaBonusEntity.setDescription(bonus);
		 CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidAreaBonusEntity);
		 CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		 covidCasesBonus = mapper.asResource(savedEntity);
		 
		return covidCasesBonus;
	}
	@Override
	public int deleteBonus(long id) throws Exception {
		log.info("deleteBonus started");

		try {

			Optional<CovidCasesBonusEntity> entityOptional = covidCasesBonusRepository.findById(id);

			log.info("Entity found == " + entityOptional.isPresent());

			if (entityOptional.isPresent()) {
				CovidCasesBonusEntity covidAreaBonusEntity = entityOptional.get();
				covidCasesBonusRepository.delete(covidAreaBonusEntity);
				return 1;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("deleteBonus() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		return 0;
	}
	@Override
	public List<String> findDuplicateNdelete() {
		log.info("findDuplicateNdelete() started");
		
		// complete the implementation below
		// ensure logic related to repo move to service implementation
		List<String> erase = covidCasesBonusRepository.findDuplicateNdelete();
				
		for (String duplicated: erase) {
			log.info ("Duplicate value found on Description Table---> " + duplicated);
			covidCasesBonusRepository.deleteBonusWithCondition(duplicated);
			log.info ("Value Deleted---> " + duplicated);
		}
		
		log.info("findDuplicateNdelete() ended");
		return erase;
	}
}
