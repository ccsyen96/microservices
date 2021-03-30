package com.app.repository.covid;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.CovidCasesBonusEntity;

//TODO: Practical bonus final
//complete this as JpaRepository
//hint: interface is similar to CovidCasesBonusRepository
public interface CovidCasesBonusRepository extends JpaRepository<CovidCasesBonusEntity, Long>  {
	
	// @transactional and modifying annotation need to be added 
	@Transactional
	@Modifying
	@Query("DELETE  FROM CovidCasesBonusEntity d WHERE d.description = :bonus")
	void deleteBonusWithCondition(String bonus);


}
