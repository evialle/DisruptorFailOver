package org.vialle.disruptorfailover.mocks;
import org.vialle.disruptorfailover.NetworkEvent;

import com.lmax.disruptor.EventHandler;

/**
 * 
 */

/**
 * @author Eric
 *
 */
public class MockServiceNetworkEventHandler implements EventHandler<NetworkEvent> {
	
	public static long value = 0;

	public void onEvent(NetworkEvent event, long sequence, boolean endOfBatch)
			throws Exception {
		value++;
		
		//StringBuilder strBuilder = new StringBuilder("MockServiceNetworkEventHandler -> ").append(event.toString()).append(" - ").append(sequence).append(", ").append(endOfBatch);
		//System.out.println(strBuilder.toString());
	}

}
