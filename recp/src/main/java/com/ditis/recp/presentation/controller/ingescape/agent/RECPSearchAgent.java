package com.ditis.recp.presentation.controller.ingescape.agent;

import org.springframework.stereotype.Service;

import com.ditis.recp.business.apiservice.TestEncapsulationAPI;
import com.ingescape.Agent;
import com.ingescape.Ioa;
import com.ingescape.IoaListener;
import com.ingescape.IoaType;
import com.ingescape.ServiceListener;

@Service
public class RECPSearchAgent implements IoaListener{
	
	private TestEncapsulationAPI recpAPIService;
	
	public void setRECPAPIService(TestEncapsulationAPI recpAPIService) {
        this.recpAPIService = recpAPIService;
    }
	
	public TestEncapsulationAPI getRECPAPIService() {
		return this.recpAPIService;
	}
	

	@Override
	public void handleIOA(Agent agent, Ioa ioa, String name, IoaType type, Object value) {		
		String patternFoundFromRECPBackend = this.getRECPAPIService().searchPatternByName(value.toString());
		System.out.println("IOA name : " + name + " and Value: " + value);
		System.out.println("patternFound = " + patternFoundFromRECPBackend);
		agent.outputSetString("patternFound", patternFoundFromRECPBackend);
	}
	
	public RECPSearchAgent() {
		recpAPIService = new TestEncapsulationAPI();
	}
}
