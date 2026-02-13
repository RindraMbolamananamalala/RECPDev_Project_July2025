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
	  * Searching and returning in a list all the patterns having the same information as the one specified by "patternInformation" parameter 
	  * and in function of the Type of Research specified by "typeOfResearch"
	  * @param typeOfResearch The Type of Research to be carried out on the patterns to be found (By Pattern's name, By Pattern's problem to solve...)  
	  * @param patternInformation The information of the patterns that have to be found 
	  * @return The list all the patterns having the same information as the one specified by "patternInformation" parameter 
	  */
	@Override
	public List<PatternEntity> findPatterns(String typeOfResearch, String patternInformation) {
		if (typeOfResearch.equals("patternResearchByName")) {
			// Research by Patterns'name
			System.out.println("Searching patterns with the name : \"" + patternInformation + "\" within the Patterns DB");
			return this.getPatternDAO().findByName(patternInformation);
		}else if (typeOfResearch.equals("patternResearchByProblemToSolve")) {
			// Research by Patterns'Problem to solve (Problem Statement)
			System.out.println("Searching patterns with the Problem to solve (Problem Statement): \"" + patternInformation + "\" within the Patterns DB");
			return this.getPatternDAO().findByProblem(patternInformation);
		}else {
			// Unknown type of research
			System.out.println("Type of Pattern Research \"" + typeOfResearch + "\" unknown, Patterns search has been stopped.");
			return null;
		}
	}

}
