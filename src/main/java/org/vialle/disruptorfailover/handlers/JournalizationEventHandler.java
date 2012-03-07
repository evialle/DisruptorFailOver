package org.vialle.disruptorfailover.handlers;

import java.io.FileNotFoundException;

import org.vialle.disruptorfailover.NetworkEvent;
import org.vialle.disruptorfailover.services.JournalisationService;

import com.lmax.disruptor.EventHandler;

public class JournalizationEventHandler implements EventHandler<NetworkEvent> {
	
	private JournalisationService service;
	
	
	public JournalizationEventHandler(JournalisationService journalisationService) throws FileNotFoundException {
		this.service = journalisationService;
	}
	
	
	public void onEvent(NetworkEvent event, long sequence, boolean endOfBatch)
			throws Exception {	
		service.journaliseEvent(event.getMarshalledObject());
	}
	

}
