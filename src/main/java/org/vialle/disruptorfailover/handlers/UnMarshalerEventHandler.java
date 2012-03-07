package org.vialle.disruptorfailover.handlers;

import org.vialle.disruptorfailover.NetworkEvent;
import org.vialle.disruptorfailover.services.MarshallerService;

import com.lmax.disruptor.EventHandler;

public class UnMarshalerEventHandler implements  EventHandler<NetworkEvent>{
		
	private MarshallerService service;

	public UnMarshalerEventHandler(MarshallerService marshallerService) {
		this.service = marshallerService;
	}

	public void onEvent(NetworkEvent event, long sequence, boolean endOfBatch)
			throws Exception {	
		Object unmarshaled = service.unMarshal(event.getMarshalledObject());
	}

	
	
}
