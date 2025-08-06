package com.ditis.recp.business.model.entity;


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
	   private String exampleDiagramImagePath;
	   
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
	   
	   public void setExampleDiagramImagePath(String exampleDiagramImagePath) {
		   this.exampleDiagramImagePath = exampleDiagramImagePath;
	   }
	   
	   public String getExampleDiagramImagePath() {
		   return this.exampleDiagramImagePath;
	   }
	   
	   /**TEMPORARY**/
	   public String getGenericDiagramImageName() {
		   return "Pattern_n" + this.getId() + "_" + this.getName() + "_genericDiagram"; 
	   }
	   
	   public String getIllustrationDiagramImageName() {
		   return "Pattern_n" + this.getId() + "_" + this.getName() + "_illustrationDiagram"; 
	   }
	
}
