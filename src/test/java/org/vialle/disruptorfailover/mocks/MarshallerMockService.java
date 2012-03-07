/**
 * 
 */
package org.vialle.disruptorfailover.mocks;

import org.vialle.disruptorfailover.services.MarshallerService;

/**
 * @author Eric
 *
 */
public class MarshallerMockService implements MarshallerService {
	
	public static long marshalCount=0;
	
	public static long unmarshalCount=0;

	public Object unMarshal(byte[] marshalledObject) {
		unmarshalCount++;
		return null;
	}

	public void marshal(Object object) {
		marshalCount++;
	}

	


	
}
