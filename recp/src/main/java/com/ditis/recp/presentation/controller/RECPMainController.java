package com.ditis.recp.presentation.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ditis.dataaccess.dao.intf.PatternDAOIntf;
import com.ditis.recp.business.model.entity.PatternEntity;

/**
 * @author Rindra Mbolamananamalala 
 */
@RestController
@EnableJpaRepositories(basePackages = "com.ditis.dataaccess.dao.intf")
public class RECPMainController{
	
	private ModelAndView mainPageMNV = null;
	
	@Autowired
	private PatternDAOIntf patternRepository;
	
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
		model.addAttribute("title", title);
		// TEST READ DB
		List<PatternEntity> patternsRead = patternRepository.findByName("MVC Name");
		for (PatternEntity pattern : patternsRead) {
			System.out.println("Pattern =" + pattern.toString());
		}
		System.out.println("NOMBRE MVC =" + patternsRead.size());
		// TEST READ DB
		return this.getMainPageMNV();
	}
}