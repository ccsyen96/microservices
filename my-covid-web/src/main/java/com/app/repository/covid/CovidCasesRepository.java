package com.app.repository.covid;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.CovidCasesAreaEntity;

public interface CovidCasesRepository extends JpaRepository<CovidCasesAreaEntity, UUID>  {
	
	// Practical Bonus 2
	//Native SQL Query
	@Query(value = "SELECT DISTINCT c.date, c.cases, c.id, c.fk_area_id FROM trx_covid_cases AS c order by date desc LIMIT 2", nativeQuery = true)
	List<CovidCasesAreaEntity> listLast2Records();
	
	//JPQL Query
	@Query("SELECT m FROM CovidCasesAreaEntity AS m order by date desc")
	List<CovidCasesAreaEntity> listLast5RecordsHQL();
	
	// Practical Bonus 3
	@Query("SELECT m FROM CovidCasesAreaEntity AS m order by date desc")
	List<CovidCasesAreaEntity> listLast5RecordsHQLWithSize(Pageable pageable);
}