package com.ditis.recp.presentation.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ditis.recp.business.as.intf.ImageASIntf;
import com.ditis.recp.business.as.intf.PatternASIntf;
import com.ditis.recp.business.model.entity.PatternEntity;

/**
 * @author Rindra Mbolamananamalala 
 */
@RestController
@EnableJpaRepositories(basePackages = "com.ditis.dataaccess.dao.intf")
@ComponentScan("com.ditis.recp.business.as.intf")
public class RECPMainController{
	
	@Value("${server.address}")
	private String serverAddress;
	
	@Value("${server.port}")
	private String serverPort;
	
	@Value("${pattern.diagramsimages.localrepository.path}")
	private String patternsDiagramsLocalImageRepositoryPath;
	
	private ModelAndView mainPageMNV = null;
	
	@Autowired
	private PatternASIntf patternAS;
	
	@Autowired 
	private ImageASIntf imageAS;
	
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
	public ModelAndView mainPage(Model model) {
		// Ensuring that the Main Page is still empty
		clearMainPage(model);
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
		try {
			String patternNameInput = inputPatternName;
			String serverImagesFolderPath = "http://" + this.serverAddress + ":" + this.serverPort + "/images/";
			// searching the patterns
			List<PatternEntity> patternsRead = patternAS.findPatterns(patternNameInput);
			// (SO FAR), only keeping the first one from the result obtained previously 
			PatternEntity patternRead = patternsRead.get(0);
			// Updating the HMI (main page) with the data obtained from the RECP's Patterns DB
		    model.addAttribute("patternName", patternRead.getName());
		    model.addAttribute("patternProblemToSolve", patternRead.getProblem());
		    // Putting (Copying) the Generic & Illustration Diagrams images within the local folder for pattern's diagrams images 
		    // which is synchronized with the Image folder of the Server (TomCat)
		    imageAS.copyImageToServerSide(
		    		patternRead.getGenericDiagramImagePath()
		    		, this.patternsDiagramsLocalImageRepositoryPath + "\\" + patternRead.getGenericDiagramImageName() + ".png"
		    );
		    imageAS.copyImageToServerSide(
		    		patternRead.getExampleDiagramImagePath()
		    		, this.patternsDiagramsLocalImageRepositoryPath + "\\" + patternRead.getIllustrationDiagramImageName() + ".png"
		    );
		    // actualizing the Main HMI with the recent Data retrieved
		    model.addAttribute("genericDiagram"
		    						, serverImagesFolderPath + patternRead.getGenericDiagramImageName() + ".png"); 
			model.addAttribute("illustrationDiagram"
								, serverImagesFolderPath + patternRead.getIllustrationDiagramImageName() + ".png");
		    //displaying the actualized version of the Main HMI
		    return this.getMainPageMNV();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// there were errors so the main Page is cleared before going back there again 
			clearMainPage(model);
			return this.getMainPageMNV();
		}
	}
	
	/**
	 * Emptying the content of the Main Page
	 * @param model The current model that corresponds to the main page
	 */
	public void clearMainPage(Model model) {
		model.addAttribute("genericDiagram", "images/default_diagram_img.svg");
		model.addAttribute("illustrationDiagram", "images/default_diagram_img.svg");
	}
}