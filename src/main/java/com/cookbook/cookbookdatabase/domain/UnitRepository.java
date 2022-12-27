package com.cookbook.cookbookdatabase.domain;

import org.springframework.data.repository.CrudRepository;

public interface UnitRepository extends CrudRepository<Unit, Long> {
	
	Unit findByUnit(String unit);

}
