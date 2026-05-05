package com.ditis.recp.presentation.controller.ingescape.agent;

import org.springframework.stereotype.Service;

import com.ingescape.Agent;
import com.ingescape.AgentEvent;
import com.ingescape.AgentEventListener;
import com.ingescape.WebSocketEvent;
import com.ingescape.WebSocketEventListener;

@Service
public class RecpEncapsulationMainAgent implements AgentEventListener, WebSocketEventListener {
	
	@Override
	public void handleAgentEvent(Agent agent, AgentEvent event, String uuid, String name, Object eventData) {
		//_logger.debug("**received agent event for {} ({}): {} with data {}", name, uuid, event, eventData);
	}

	@Override
	public void handleWebSocketEvent(WebSocketEvent event, Throwable t) {
//		if (t != null) { // (event == WebSocketEvent.IGS_WEB_SOCKET_FAILED)
//			_logger.error("**received web socket event {} with exception {}", event, t.toString());
//		}
//		else {
//			_logger.debug("**received web socket event {}", event);
//		}
	}
	
}
