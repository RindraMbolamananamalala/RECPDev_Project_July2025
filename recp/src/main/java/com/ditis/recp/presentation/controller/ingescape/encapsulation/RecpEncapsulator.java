package com.ditis.recp.presentation.controller.ingescape.encapsulation;

import com.ditis.recp.presentation.controller.ingescape.agent.RECPSearchAgent;
import com.ditis.recp.presentation.controller.ingescape.agent.RecpEncapsulationMainAgentListener;
import com.ingescape.Agent;
import com.ingescape.Global;
import com.ingescape.IoaType;

/**
 *  @author Rindra Mbolamananamalala
 *  This class is responsible for encapsulating the RECP Tool as an Ingescape Agent. 
 *  It initializes the necessary agents, sets up the communication channels, and launches the encapsulation agent within the Ingescape environment.
 */
public class RecpEncapsulator {
	
	// The Global context of the Ingescape environment, used for managing agents and communication.
	private Global globalContext;
	
	// The main listener for the RECP encapsulation agent, responsible for handling events and interactions within the Ingescape environment.
	private RecpEncapsulationMainAgentListener recpEncapsulationMainAgentListener;
	
	// The main agent responsible for encapsulating the RECP Tool within the Ingescape environment.
	private Agent recpEncapsulationAgent;
	
	// The agent dedicated to handling the S(of SCRUD) service of the RECP Tool, specifically for searching patterns by name or problem.
	private RECPSearchAgent recpSearchAgent;
	
	public void setGlobalContext(Global globalContext) {
		this.globalContext = globalContext;
	}
	
	public Global getGlobalContext() {
		return globalContext;
	}
	
	public void setRecpEncapsulationMainAgentListener(RecpEncapsulationMainAgentListener recpEncapsulationMainAgentListener) {
		this.recpEncapsulationMainAgentListener = recpEncapsulationMainAgentListener;
	}
	
	public RecpEncapsulationMainAgentListener getRecpEncapsulationMainAgentListener() {
		return this.recpEncapsulationMainAgentListener;
	}
	
	public void setRecpEncapsulationAgent(Agent recpEncapsulationAgent) {
		this.recpEncapsulationAgent = recpEncapsulationAgent;
	}
	
	public Agent getRecpEncapsulationAgent() {
		return this.recpEncapsulationAgent;
	}
	
	public void setRecpSearchAgent(RECPSearchAgent recpSearchAgent) {
		this.recpSearchAgent = recpSearchAgent;
	}
	
	public RECPSearchAgent getRecpSearchAgent() {
		return this.recpSearchAgent;
	}
	
	/**
	 * This method initializes and launches the RECP Tool as an Ingescape Agent. 
	 * It sets up the necessary agents, defines their inputs and outputs, and establishes the communication channels for interaction with the 
	 * Ingescape environment.
	 */
	public void launchRECPAsAnIngescapeAgent() {
		// Initializing the Main RECP Agent 
		this.setRecpEncapsulationMainAgentListener(new RecpEncapsulationMainAgentListener());
		
		// Preparing the Web Socket-based communication with Ingescape 
		this.setGlobalContext(new Global("ws://localhost:3000"));
		this.getGlobalContext().observeWebSocketEvents(this.getRecpEncapsulationMainAgentListener());
		
		// Preparing the Agent dedicated to the encapsulation (The Agent with the highest level of encapsulation)
		this.setRecpEncapsulationAgent(this.getGlobalContext().agentCreate("recpEncapsulationAgent"));
		this.getRecpEncapsulationAgent().observeAgentEvents(recpEncapsulationMainAgentListener);
		
		// Initializing the RECP's Agent dedicated to the S (from SCRUD) of patterns
		this.setRecpSearchAgent(new RECPSearchAgent());
		
		// Actual encapsulation of the RECP's Agents
		this.getRecpEncapsulationAgent().definition.setName("RECP Tool Agent");
		this.getRecpEncapsulationAgent().definition.setDescription("The Agent encapsulating the RECP Tool");
		this.getRecpEncapsulationAgent().definition.inputCreate("patternSearchByName", IoaType.IGS_STRING_T);
		this.getRecpEncapsulationAgent().definition.inputCreate("patternSearchByProblem", IoaType.IGS_STRING_T);
		this.getRecpEncapsulationAgent().definition.outputCreate("patternFound", IoaType.IGS_STRING_T);
		// Coupling the Encapsulation Agent's input to the RECP Search Agent's input	  
		this.getRecpEncapsulationAgent().observeInput("patternSearchByName", this.getRecpSearchAgent());
		
		
		// launching the encapsulation agent
		this.getRecpEncapsulationAgent().start();
		
		System.out.println("RECP Agent launched successfully under Ingescape!");
	}

}
