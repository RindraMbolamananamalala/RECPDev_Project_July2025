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
	 * Searching and displaying the list of patterns that have the same name as that specified by the user within the 'Pattern's name" input text 
	 * @param inputPatternName The name of the patterns to SEARCH
	 * @param model The current model being used by the RECP Application
	 * @return The actualized (with patterns' data retrieved from the RECP's patterns DB) version of the main page (displayed)
	 */
	@PostMapping(value="/search_and_display_patterns")
	public ModelAndView researchAndDisplayPattern(String typeOfPatternResearch, String inputPatternInformation, Model model) {
		// Setting up local variables
		String serverImagesFolderPath = "http://" + this.serverAddress + ":" + this.serverPort + "/images/";
		try {
			
			// launching the SEARCH of Patterns
			PatternEntity patternRead = this.launchPatternSearch(typeOfPatternResearch, inputPatternInformation, model);
			
		    // Putting (Copying) the Generic & Illustration Diagrams images within the local folder for pattern's diagrams images 
		    // which is synchronized with the Image folder of the Server (TomCat)
		    /** VERY TEMPORARY, only 2 examples of applications diagrams are handled by the available version of the RECP **/
		    imageAS.copyImageToServerSide(
		    		patternRead.getGenericDiagramImagePath()
		    		, this.patternsDiagramsLocalImageRepositoryPath + "\\" + patternRead.getGenericDiagramImageName() + ".png"
		    );
		    imageAS.copyImageToServerSide(
		    		patternRead.getExamplesDiagramsImagesPaths().get(0)
		    		, this.patternsDiagramsLocalImageRepositoryPath + "\\" + patternRead.getIllustrationDiagramImageName(1) + ".png"
		    );
		    imageAS.copyImageToServerSide(
		    		patternRead.getExamplesDiagramsImagesPaths().get(1)
		    		, this.patternsDiagramsLocalImageRepositoryPath + "\\" + patternRead.getIllustrationDiagramImageName(2) + ".png"
		    );
		    // actualizing the Main HMI with the recent Data retrieved
		    model.addAttribute("genericDiagram"
		    						, serverImagesFolderPath + patternRead.getGenericDiagramImageName() + ".png"); 
			model.addAttribute("illustrationDiagram1"
								, serverImagesFolderPath + patternRead.getIllustrationDiagramImageName(1) + ".png");
			model.addAttribute("illustrationDiagram2"
					, serverImagesFolderPath + patternRead.getIllustrationDiagramImageName(2) + ".png");
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
	 * Searching the list of patterns that have the same name as that specified by the user within the 'Pattern's name" input text 
	 * @param inputPatternName The name of the patterns to SEARCH
	 * @param model The current model being used by the RECP Application
	 * @return (VERY TEMPORARY) The first Pattern Entity of the list of patterns found from the specified name
	 */
	@GetMapping(value="/search_patterns", params = "inputPatternName", name = "test_search_pattern")
	public PatternEntity researchPattern(@RequestParam String typeOfResearch, @RequestParam String inputPatternName, Model model) {
		try {
			return this.launchPatternSearch(typeOfResearch, inputPatternName, model);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.print(e.getMessage());
			return null;
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
	
	/**
	 * Launching the actual process and activities of Pattern SEARCH from a pattern's information specified as argument and in function
	 * of the type of research chosen by the User specified under "typeOfResearch"  
	 * @param inputPatternName The name of the patterns to SEARCH
	 * @param model The current model (M of MVC pattern) being used by the RECP Application
	 * @return (VERY TEMPORARY) The first Pattern Entity of the list of patterns found from the specified name
	 */
	private PatternEntity launchPatternSearch(String typeOfResearch, String inputPatternName, Model model) {
		try {
			String patternNameInput = inputPatternName;
			// searching the patterns
			List<PatternEntity> patternsRead = patternAS.findPatterns(typeOfResearch, patternNameInput);
			// (SO FAR), only keeping the first one from the result obtained previously 
			PatternEntity patternRead = patternsRead.get(0);
			// Updating the Model part ("M" of the MVC pattern) with the data obtained from the RECP's Patterns DB
		    model.addAttribute("patternName", patternRead.getName());
		    model.addAttribute("patternProblemToSolve", patternRead.getProblem());
		    model.addAttribute("patternSolutionStatement", patternRead.getSolutionStatement());
		    model.addAttribute("patternForActivityObjectives", patternRead.getPatternForActivityObjectives());
		    model.addAttribute("patternForDTSPurposes", patternRead.getPatternForDTSPurposes());
		    model.addAttribute("patternGeneralPurpose", patternRead.getPatternGeneralPurpose());
		    model.addAttribute("patternSystemLifeCycleRelevance", patternRead.getPatternSystemLifeCycleRelevance());
		    model.addAttribute("patternScenarioOfApplicabilityStatement", patternRead.getScenarioOfApplicabilityStatement());
		    model.addAttribute("patternActualSituationsFromWhichThePatternWasDeduced", patternRead.getActualSituationsFromWhichThePatternWasDeduced());
		    model.addAttribute("patternConsequencesOfApplicationStatements", patternRead.getConsequencesOfApplicationStatements());
		    model.addAttribute("patternImplementationsHintsStatements", patternRead.getImplementationsHintsStatements());
		    
		    /** VERY TEMPORARY, only 2 examples of applications statements are handled by the available version of the RECP **/
		    model.addAttribute("patternExampleOfApplicationStatements1", patternRead.getExamplesOfApplicationsStatements().get(0));
		    model.addAttribute("patternExampleOfApplicationStatements2", patternRead.getExamplesOfApplicationsStatements().get(1));
		    return patternRead;
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return null;
		}
	}
}