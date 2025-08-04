package com.ditis.recp.presentation.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ditis.recp.business.as.intf.PatternASIntf;
import com.ditis.recp.business.model.entity.PatternEntity;

/**
 * @author Rindra Mbolamananamalala 
 */
@RestController
@EnableJpaRepositories(basePackages = "com.ditis.dataaccess.dao.intf")
@ComponentScan("com.ditis.recp.business.as.intf")
public class RECPMainController{
	
	private ModelAndView mainPageMNV = null;
	
	@Autowired
	private PatternASIntf patternAS;
	
	public void setMainPageMNV(ModelAndView mainPageMNV) {
		this.mainPageMNV = mainPageMNV;
	}
	
	/**
	 * Setting up a SINGLETON-based Model and View of the main page 
//	 * @return The UNIQUE instance of the Model and View of the main page 
	 */
	public ModelAndView getMainPageMNV() {
		if (this.mainPageMNV == null) {
			this.mainPageMNV = new ModelAndView("index.html");
		}
		return mainPageMNV;
	}
	
	/**
	 * Displaying the main page
	 * @return
	 */
	@GetMapping("/")
	public ModelAndView mainPage() {
		return this.getMainPageMNV();
	}
	
	/**
	 * Searching the list of patterns that have the same name as that specified by the user within the 'Pattern's name" input text 
	 * @param inputPatternName The name of the patterns to search
	 * @param model The current model being used by the RECP Application
	 * @return The actualized (with patterns' data retrieved from the RECP's patterns DB) version of the main page
	 */
	@PostMapping(value="/search_patterns")
	public ModelAndView researchPattern(String inputPatternName, Model model) {
		String patternNameInput = inputPatternName;
		// searching the patterns
		List<PatternEntity> patternsRead = patternAS.findPatterns(patternNameInput);
		// (SO FAR), only keeping the first one from the result obtained previously 
		PatternEntity patternRead = patternsRead.get(0);
		// Updating the HMI (main page) with the data obtained from the RECP's Patterns DB
	    model.addAttribute("patternName", patternRead.getName());
	    model.addAttribute("patternProblemToSolve", patternRead.getProblem());
	    
	    //displaying the actualized version of the Main HMI
	    return this.getMainPageMNV();
	}
}