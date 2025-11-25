package com.ditis.recp.business.model.entity;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The class defining the Entity "Pattern" 
 */
@Entity
@Table(name = "pattern")
public class PatternEntity extends RECPGenericEntity{

	   private String name;
	   private String problem;
	   private String solutionStatement;
	   private String patternForActivityObjectives;
	   private String patternForDtsPurposes;
	   private String patternGeneralPurpose;
	   private String patternSystemLifeCycleRelevance;
	   private String scenarioOfApplicabilityStatement;
	   private String actualSituationsFromWhichThePatternWasDeduced;
	   private String consequencesOfApplicationStatements;
	   private String genericDiagramImagePath;
	   private String examplesOfApplicationsStatements;
	   private String examplesDiagramsImagesPaths;
	   private String implementationsHintsStatements;
	   
	   

	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;

	   
	   public void setId(Long id) {
		   this.id = id;
	   }
	   
	   public Long getId() {
		   return this.id;
	   }
	   
	   
	   public void setName(String name) {
		   this.name = name;
	   }
	   
	   public String getName() {
		   return this.name;
	   }
	   
	   public void setProblem(String problem) {
		   this.problem = problem;
	   }
	   
	   public String getProblem() {
		   return this.problem;
	   }
	   
	   
	   public void setSolutionStatement(String solutionStatement) {
		   this.solutionStatement = solutionStatement;
	   }
		   	   
	   public String getSolutionStatement() {
		   return this.solutionStatement;
	   }

	   public void setPatternForActivityObjectives(String patternForActivityObjectives) {
		   this.patternForActivityObjectives = patternForActivityObjectives;
	   }
		   	   
	   public String getPatternForActivityObjectives() {
		   return this.patternForActivityObjectives;
	   }

	   public void setPatternForDTSPurposes(String patternForDTSPurposes) {
		   this.patternForDtsPurposes = patternForDTSPurposes;
	   }
		   	   
	   public String getPatternForDTSPurposes() {
		   return this.patternForDtsPurposes;
	   }

	   public void setPatternGeneralPurpose(String patternGeneralPurpose) {
		   this.patternGeneralPurpose = patternGeneralPurpose;
	   }
		   	   
	   public String getPatternGeneralPurpose() {
		   return this.patternGeneralPurpose;
	   }

	   public void setPatternSystemLifeCycleRelevance(String patternSystemLifeCycleRelevance) {
		   this.patternSystemLifeCycleRelevance = patternSystemLifeCycleRelevance;
	   }
		   	   
	   public String getPatternSystemLifeCycleRelevance() {
		   return this.patternSystemLifeCycleRelevance;
	   }

	   public void setScenarioOfApplicabilityStatement(String scenarioOfApplicabilityStatement) {
		   this.scenarioOfApplicabilityStatement = scenarioOfApplicabilityStatement;
	   }
		   	   
	   public String getScenarioOfApplicabilityStatement() {
		   return this.scenarioOfApplicabilityStatement;
	   }

	   public void setActualSituationsFromWhichThePatternWasDeduced(String actualSituationsFromWhichThePatternWasDeduced) {
		   this.actualSituationsFromWhichThePatternWasDeduced = actualSituationsFromWhichThePatternWasDeduced;
	   }
		   	   
	   public String getActualSituationsFromWhichThePatternWasDeduced() {
		   return this.actualSituationsFromWhichThePatternWasDeduced;
	   }

	   public void setConsequencesOfApplicationStatements(String consequencesOfApplicationStatements) {
		   this.consequencesOfApplicationStatements = consequencesOfApplicationStatements;
	   }
		   	   
	   public String getConsequencesOfApplicationStatements() {
		   return this.consequencesOfApplicationStatements;
	   }
	   
	   public void setImplementationsHintsStatements(String implementationsHintsStatements) {
		   this.implementationsHintsStatements = implementationsHintsStatements;
	   }
		   	   
	   public String getImplementationsHintsStatements() {
		   return this.implementationsHintsStatements;
	   }

	   
	   public void setGenericDiagramImagePath(String genericDiagramImagePath) {
		   this.genericDiagramImagePath = genericDiagramImagePath;
	   }
	   
	   public String getGenericDiagramImagePath() {
		   return this.genericDiagramImagePath;
	   }
	   
	   public void setExamplesOfApplicationsStatements(String examplesOfApplicationsStatements){
		   this.examplesOfApplicationsStatements = examplesOfApplicationsStatements;
	   }
	   
	   /**
	    * 
	    * @return The list of examples of applications statements related to the current Pattern object  
	    */
	   public List<String> getExamplesOfApplicationsStatements() {
		   //transforming the String value into its equivalent in List
		   List<String> listToReturn = new ArrayList<String>();
		   // only taking into account non-void string (because a statement can't be void) 
		   List<String> rawListOfStatements = Arrays.asList(this.examplesOfApplicationsStatements.split("\\[statement\\]"));
		   for (String s : rawListOfStatements)
			   if (!s.equals(""))
				   listToReturn.add(s);
		   return listToReturn;
	   }

	   
	   public void setExamplesDiagramsImagesPaths(String examplesDiagramsImagesPaths) {
		   this.examplesDiagramsImagesPaths = examplesDiagramsImagesPaths;
	   }
	   
	   public List<String> getExamplesDiagramsImagesPaths() {
		   //transforming the String value into its equivalent in List
		   return Arrays.asList(this.examplesDiagramsImagesPaths.split(";"));
	   }
	   
	   /**VERY TEMPORARY**/
	   public String getGenericDiagramImageName() {
		   return "Pattern_n" + this.getId() + "_" + this.getName() + "_genericDiagram"; 
	   }
	   
	   public String getIllustrationDiagramImageName(int illustrationNumber) {
		   return "Pattern_n" + this.getId() + "_" + this.getName() + "_illustrationDiagram" + illustrationNumber; 
	   }
	
}
