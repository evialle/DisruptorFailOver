package org.vialle.disruptorfailover;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;

import org.vialle.disruptorfailover.handlers.AcknowledgeEventHandler;
import org.vialle.disruptorfailover.handlers.JournalizationEventHandler;
import org.vialle.disruptorfailover.handlers.UnMarshalerEventHandler;
import org.vialle.disruptorfailover.handlers.UnMarshallerExceptionHandler;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;

public class DisruptorService {
	
	private Disruptor<NetworkEvent> disruptor;
	
	private String filePath = "c:/tmp/test.txt";
	
	private JournalizationEventHandler jzHandler;
	
	private UnMarshalerEventHandler umHandler;
	
	private UnMarshallerExceptionHandler umExceptionHandler;
	
	private EventHandler<NetworkEvent> ackEventHandler;
	
	private RingBuffer<NetworkEvent> ringBuffer;
	
	private ExecutorService executorService;
	
	DisruptorService(final ConfigDisruptor cd) throws FileNotFoundException {
		
		this.disruptor = new Disruptor<NetworkEvent>(NetworkEvent.FACTORY, cd.getExecutorService(), cd.getClaimStrategy(), cd.getWaitingStrategy());
		
		this.executorService = cd.getExecutorService();
		
		this.ringBuffer = disruptor.getRingBuffer();
		
		jzHandler = new JournalizationEventHandler(cd.getJournalisationService());
		umHandler = new UnMarshalerEventHandler(cd.getMarshallerService());
		umExceptionHandler = new UnMarshallerExceptionHandler(cd.getMarshallerService());
		
		ackEventHandler = new AcknowledgeEventHandler();
		
		disruptor.handleEventsWith(jzHandler, umHandler);
		disruptor.handleExceptionsFor(umHandler).with(umExceptionHandler);
		
	}
	
	/**
	 * Get an instance of the disruptor.
	 * 
	 * @return
	 */
	public Disruptor<NetworkEvent> getDisruptor() {
		return this.disruptor;
	}
	
	/**
	 * Stop the disruptor service. (synchronizing this method could be silly in a mono threaded app, but this method is
	 * called once)
	 */
	public void stop() {
		if (this.disruptor != null) {
			this.disruptor.shutdown();
			this.executorService.shutdownNow();
			
			this.executorService = null;
			this.disruptor = null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public EventHandlerGroup<NetworkEvent> getNetworkHandlerEventGroup() {
		return getDisruptor().after(jzHandler).and(umHandler);
	}
	
	public void sendData(final byte[] bytes) {
		// 1- Init Event
		long sequence = ringBuffer.next();
		NetworkEvent networkEvent = ringBuffer.get(sequence);
		
		// 2- Populate Object
		networkEvent.setMarshalledObject(bytes);
		
		// 3- Send it
		ringBuffer.publish(sequence);
	}
	
	/**
	 * Start the disruptor (synchronizing this method could be silly in a mono threaded app, but this method is called
	 * once)
	 */
	public void start() {
		if (getDisruptor() != null) {
			
			ringBuffer = getDisruptor().getRingBuffer();
			
			getDisruptor().start();
		}
	}
	
	void setDisruptor(Disruptor<NetworkEvent> disruptor2) {
		this.disruptor = disruptor2;
	}
	
	public EventHandlerGroup<NetworkEvent> handleEventsWith(EventHandler<NetworkEvent> serviceNetworkEventHandler) {
		return getDisruptor().after(jzHandler, umHandler).then(serviceNetworkEventHandler);
	}
}
