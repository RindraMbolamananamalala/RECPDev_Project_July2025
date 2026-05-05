package com.ditis.recp.presentation.controller.ingescape.encapsulation;

import com.ditis.recp.presentation.controller.ingescape.agent.RECPSearchAgent;
import com.ditis.recp.presentation.controller.ingescape.agent.RecpEncapsulationMainAgent;
import com.ingescape.Agent;
import com.ingescape.Global;
import com.ingescape.IoaType;

public class RecpEncapsulator {
	
	public static void main(String []args) {
		
		// Initializing the Main RECP Agent 
		RecpEncapsulationMainAgent recpEncapsulationMainAgent = new RecpEncapsulationMainAgent();
		
		// Preparing the Web Socket-based communication with Ingescape 
		Global globalContext = new Global("ws://localhost:8000");
		globalContext.observeWebSocketEvents(recpEncapsulationMainAgent);
		
		// Preparing the Agent dedicated to the encapsulation
		Agent recpEncapsulationAgent = globalContext.agentCreate("recpEncapsulationAgent");
		recpEncapsulationAgent.observeAgentEvents(recpEncapsulationMainAgent);
		
		// Initializing the RECP's Agent dedicated to the S (from SCRUD) of patterns
		RECPSearchAgent recpSearchAgent = new RECPSearchAgent();
		
		// Actual encapsulation of the RECP's Agents
		recpEncapsulationAgent.definition.setName("RECPTool");
		recpEncapsulationAgent.definition.setDescription("The Agent encapsulating the RECP Tool");
		recpEncapsulationAgent.definition.inputCreate("patternSearchByName", IoaType.IGS_STRING_T);
		recpEncapsulationAgent.definition.inputCreate("patternSearchByProblem", IoaType.IGS_STRING_T);
		recpEncapsulationAgent.definition.outputCreate("patternFound", IoaType.IGS_STRING_T);
		recpEncapsulationAgent.observeInput("patternSearchByName", recpSearchAgent);
		
		
		// launching the encapsulation agent
		recpEncapsulationAgent.start();
	}

}
