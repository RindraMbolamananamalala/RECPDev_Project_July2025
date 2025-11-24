package com.ditis.recp.business.model.entity;



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
	   private String genericDiagramImagePath;
	   private String examplesDiagramsImagesPaths;

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
	   
	   public void setGenericDiagramImagePath(String genericDiagramImagePath) {
		   this.genericDiagramImagePath = genericDiagramImagePath;
	   }
	   
	   public String getGenericDiagramImagePath() {
		   return this.genericDiagramImagePath;
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
