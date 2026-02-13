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
	  * Searching and returning in a list all the patterns having the same information as the one specified by "patternInformation" parameter 
	  * and in function of the Type of Research specified by "typeOfResearch"
	  * @param typeOfResearch The Type of Research to be carried out on the patterns to be found (By Pattern's name, By Pattern's problem to solve...)  
	  * @param patternInformation The information of the patterns that have to be found 
	  * @return The list all the patterns having the same information as the one specified by "patternInformation" parameter 
	  */
	  public List<PatternEntity> findPatterns(String typeOfResearch, String patternInformation);
}
