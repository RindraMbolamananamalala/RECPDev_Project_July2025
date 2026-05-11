package com.ditis.recp.business.apiservice.internalapiservice.intf;

import org.springframework.stereotype.Service;

/**
 * The Abstract Class regrouping any Internal RECP SCRUD-related API Service 
 * @author Rindra Mbolamananamalala
 */
public interface RECPSCRUDAPIServiceIntf {
	 
	 /**
	  * Searching a pattern from its name
	  * @param patternName The name of the pattern to SEARCH (S of SCRUD)
	  * @return The String version of the pattern entity returned from the RECP DB
	  */
	 public String searchPatternByName(String patternName);

}
