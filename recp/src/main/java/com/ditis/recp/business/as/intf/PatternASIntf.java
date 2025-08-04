package com.ditis.recp.business.as.intf;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ditis.recp.business.model.entity.PatternEntity;

/**
 * The Abstract Class regrouping any Pattern-related Application Service 
 * @author Rindra Mbolamananamalala
 */
 @Service
public interface PatternASIntf {
	
	/**
	 * Searching and returning in a list all the patterns having the same name as the one specified by "patternName" parameter  
	 * @param patternName The name of the patterns that have to be found 
	 * @return The list all the patterns having the same name as the one specified by "patternName" parameter 
	 */
	public List<PatternEntity> findPatterns(String patternName);
}
