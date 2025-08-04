package com.ditis.recp.presentation.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/")
	public ModelAndView mainPage(
			@RequestParam(name="title"
								, required=false
								, defaultValue="Référentiel d'Expertise, de Connaissances et de Pratiques"
							) String title
		, Model  model) {
		System.out.println("HERERERE=");
		model.addAttribute("title", title);
		// TEST READ DB
		List<PatternEntity> patternsRead = patternAS.findPatterns("MVC Name");
		for (PatternEntity pattern : patternsRead) {
			System.out.println("Pattern now as=" + pattern.toString());
		}
		System.out.println("NOMBRE MVC now AS=" + patternsRead.size());
		// TEST READ DB
		return this.getMainPageMNV();
	}
}