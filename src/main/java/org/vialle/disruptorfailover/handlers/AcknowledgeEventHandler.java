/**
 * 
 */
package org.vialle.disruptorfailover.handlers;

import org.vialle.disruptorfailover.NetworkEvent;

import com.lmax.disruptor.EventHandler;

/**
 * @author Eric
 *
 */
public class AcknowledgeEventHandler implements EventHandler<NetworkEvent> {
	
	/* (non-Javadoc)
	 * @see com.lmax.disruptor.EventHandler#onEvent(java.lang.Object, long, boolean)
	 */
	public void onEvent(NetworkEvent event, long sequence, boolean endOfBatch) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
