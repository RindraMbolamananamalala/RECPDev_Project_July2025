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
	@GetMapping(value="/search_and_display_patterns")
	public ModelAndView researchAndDisplayPattern(String typeOfPatternResearch, String inputPatternInformation, Model model) {
		// Setting up local variables
		//String serverImagesFolderPath = "http://" + this.serverAddress + ":" + this.serverPort + "/images/";
		String serverImagesFolderPath = "/images/"; 
		try {
			
			// launching the SEARCH of Patterns
			PatternEntity patternRead = this.launchPatternSearch(typeOfPatternResearch, inputPatternInformation, model);
			
		    // Putting (Copying) the Generic & Illustration Diagrams images within the local folder for pattern's diagrams images 
		    // which is synchronized with the Image folder of the Server (TomCat)
		    /** VERY TEMPORARY, only 2 examples of applications diagrams are handled by the available version of the RECP **/
		    
			String genericFileName = sanitizeFilename(patternRead.getGenericDiagramImageName());
			String illustration1FileName = sanitizeFilename(patternRead.getIllustrationDiagramImageName(1));
			String illustration2FileName = sanitizeFilename(patternRead.getIllustrationDiagramImageName(2));
			
//			imageAS.copyImageToServerSide(
//				    patternRead.getGenericDiagramImagePath(), 
//				    this.patternsDiagramsLocalImageRepositoryPath + "/" + genericFileName
//				);
//				imageAS.copyImageToServerSide(
//				    patternRead.getExamplesDiagramsImagesPaths().get(0), 
//				    this.patternsDiagramsLocalImageRepositoryPath + "/" + illustration1FileName
//				);
//				imageAS.copyImageToServerSide(
//				    patternRead.getExamplesDiagramsImagesPaths().get(1), 
//				    this.patternsDiagramsLocalImageRepositoryPath + "/" + illustration2FileName
//				);
				
		    // actualizing the Main HMI with the recent Data retrieved
			model.addAttribute("genericDiagram", serverImagesFolderPath + genericFileName);
			model.addAttribute("illustrationDiagram1", serverImagesFolderPath + illustration1FileName);
			model.addAttribute("illustrationDiagram2", serverImagesFolderPath + illustration2FileName);
				
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
	@GetMapping(value="/search_patterns")
	public PatternEntity researchPattern(@RequestParam String typeOfPatternResearch, @RequestParam String inputPatternInformation, Model model) {
		try {
			return this.launchPatternSearch(typeOfPatternResearch, inputPatternInformation, model);
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
		    // Displaying all the alternative patterns 
		    this.displayRelatedPatternsOnTheMainPage(patternRead.getAlternativePatterns(), "alternativePatternsLinksText", model);
		    // Displaying all the complementary patterns 
		    this.displayRelatedPatternsOnTheMainPage(patternRead.getComplementaryPatterns(), "complementaryPatternsLinksText", model);
		    // Displaying all the anti-patterns 
		    this.displayRelatedPatternsOnTheMainPage(patternRead.getAntiPatterns(), "antiPatternsLinksText", model);
		    
		    
		    return patternRead;
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Displaying the list of related patterns (with the respective hyperlink leading to their own READ-mode HMI)  
	 * on the main page in function of the relationship type 
	 * @param relatedPatternsNames The list of related patterns'names
	 * @param relationshipSpecificLinksTextElement The text-area HTML element (Thymeleaf Object)dedicated to the type of relationship  
	 * @param model The current model (M of MVC pattern) being used by the RECP Application
	 */
	public void displayRelatedPatternsOnTheMainPage(List<String> relatedPatternsNames
													, String relationshipSpecificLinksTextElement
													, Model model) {
		 	String patternsText = "";
		    for(String patternName : relatedPatternsNames) {
		    	patternsText += "</BR>" 
		    								+ "<a href=\""
		    										+ "http://" + "recptool-hwfpgdgvakedevb0.westus3-01.azurewebsites.net"
		    										+ "/search_and_display_patterns" 
		    										+ "?" 
		    										+ "typeOfPatternResearch=patternResearchByName" 
		    										+ "&" 
		    										+ "inputPatternInformation=" + patternName 
		    									+ "\">" 
		    										+ patternName 
		    								+ "</a>";
		    }
		    model.addAttribute(relationshipSpecificLinksTextElement, patternsText);
	}
	
	//VERY TEMPORARY
	/**
	 * Converting a Windows path (from the DB) into a relative web path (to be used
	 * within the Web HMI)
	 * 
	 * @param pathFromDb The path of the image file as stored in the RECP's Patterns
	 *                   DB
	 * @return The relative web path of the image file to be used within the Web HMI
	 */
	public String getWebImageUrl(String pathFromDb) {
	    if (pathFromDb == null || pathFromDb.isEmpty()) {
	        return "/images/default.png";
	    }
	    // 1. Extract only the filename from the Windows path (handling both \ and /)
	    String fileName = pathFromDb.substring(pathFromDb.lastIndexOf("\\") + 1);
	    fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
	    // 2. Return the relative web path that matches our WebConfig mapping
	    // This will result in something like "/images/Pattern_n9_EARS.png"
	    return "/images/" + fileName;
	}
	
	/**
	 * Sanitizing the filename to ensure it is safe for use in URLs and file
	 * systems. This method removes unwanted characters, replaces spaces with
	 * underscores, and ensures the filename ends with a .png extension.
	 * 
	 * @param dbName The original filename from the database.
	 * @return A sanitized version of the filename suitable for use in URLs and file
	 *         systems.
	 */
	private String sanitizeFilename(String dbName) {
	    if (dbName == null || dbName.isEmpty()) return "default_diagram.png";
	    // 1. Supprime les parenthèses et leur contenu
	    String cleanName = dbName.replaceAll("\\(.*?\\)", "");
	    // 2. Supprime les apostrophes et caractères spéciaux (comme dans EARS')
	    cleanName = cleanName.replaceAll("['’‘]", "");
	    // 3. Remplace " Design Pattern" et " Requirements pattern" par rien
	    cleanName = cleanName.replace(" Design Pattern", "").replace(" Requirements pattern", "");
	    // 4. Remplace TOUT ce qui n'est pas une lettre ou un chiffre par un seul underscore
	    cleanName = cleanName.replaceAll("[^a-zA-Z0-9]+", "_");
	    // 5. Nettoie les underscores en double ou en fin de chaîne
	    cleanName = cleanName.replaceAll("_+", "_").replaceAll("_$", "");
	    return cleanName.toLowerCase() + ".png"; // Passage en minuscule pour éviter les surprises Linux
	}
}