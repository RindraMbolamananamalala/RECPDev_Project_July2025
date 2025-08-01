package com.ditis.dataaccess.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.JpaRepositoryNameSpaceHandler;
import org.springframework.stereotype.Service;

import com.ditis.dataaccess.dao.intf.PatternDAOIntf;
import com.ditis.recp.business.model.entity.PatternEntity;
import com.ditis.recp.dataaccess.PatternRepository;

/**
 * The Concrete Class regrouping any DAO(Data Access Object)-based service related to a pattern 
 * (Application of the DAO pattern on a "pattern" object). 
 */
@Service
public class PatternDAOImpl {
	
	@Autowired
	private PatternRepository patternRepository;
	
	public PatternEntity createPattern(PatternEntity patternEntity) {
		return patternRepository.save(patternEntity);
	}
	
	
}
