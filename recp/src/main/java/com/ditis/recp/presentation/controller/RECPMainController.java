package com.ditis.recp.presentation.controller;

import javax.swing.JOptionPane;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RECPMainController{
	
	private ModelAndView mainPageMNV = null;
	
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
		return this.getMainPageMNV();
	}
}