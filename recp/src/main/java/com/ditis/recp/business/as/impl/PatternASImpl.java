package com.ditis.recp.business.as.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import com.ditis.dataaccess.dao.intf.PatternDAOIntf;
import com.ditis.recp.business.as.intf.PatternASIntf;
import com.ditis.recp.business.model.entity.PatternEntity;

/**
 * The Concrete Class regrouping any Pattern-related Application Service 
 *  @author Rindra Mbolamananamalala
 */
@Service
public class PatternASImpl implements PatternASIntf {
	
	@Autowired
	private PatternDAOIntf patternDAO;
	
	public PatternDAOIntf getPatternDAO() {
		return this.patternDAO;
	}
	
	/**
	 * Searching and returning in a list all the patterns having the same name as the one specified by "patternName" parameter  
	 * @param patternName The name of the patterns that have to be found 
	 * @return The list all the patterns having the same name as the one specified by "patternName" parameter 
	 */
	@Override
	public List<PatternEntity> findPatterns(String patternName) {
		return this.getPatternDAO().findByName(patternName);
	}

}
