package org.vialle.disruptorfailover;

import java.io.FileNotFoundException;
import java.util.concurrent.Executors;

import org.junit.Assert;
import org.junit.Test;
import org.vialle.disruptorfailover.mocks.JournalizationMockService;
import org.vialle.disruptorfailover.mocks.MarshallerMockService;
import org.vialle.disruptorfailover.mocks.MockServiceNetworkEventHandler;


/**
 * @author Eric
 * 
 */
public class DisruptorServiceTest {
	
	private static final int NB_OF_REQUESTS = 100000;

	@Test
	@SuppressWarnings("unchecked")
	public void sendData() throws FileNotFoundException {
		
		ConfigDisruptor cd = ConfigDisruptor.createConfig().setJournalisationService(new JournalizationMockService())
				.setMarshallerService(new MarshallerMockService()).setJournalisationService(new JournalizationMockService()).setExecutorService(Executors.newCachedThreadPool());
		
		DisruptorService disruptorService = cd.createDisruptorService();
		
		disruptorService.handleEventsWith(new MockServiceNetworkEventHandler());
		disruptorService.start();
		
		byte[] bytes = new byte[] { 0, 2, 5, 6 };
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < NB_OF_REQUESTS; i++) {
			disruptorService.sendData(bytes);
		}
		long stopTime = System.currentTimeMillis();

		disruptorService.stop();
		
		System.out.println(">> Duration (ms): " + (stopTime-startTime) + " -> (msg/sec)" +  NB_OF_REQUESTS/((stopTime-startTime)/1000.));
		
		Assert.assertEquals(NB_OF_REQUESTS, MockServiceNetworkEventHandler.value);
		Assert.assertEquals( NB_OF_REQUESTS, MarshallerMockService.unmarshalCount);
		Assert.assertEquals(NB_OF_REQUESTS, JournalizationMockService.journalizeCount);

	}
	
}
