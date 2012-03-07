package org.vialle.disruptorfailover.mocks;

import org.vialle.disruptorfailover.services.JournalisationService;

public class JournalizationMockService implements JournalisationService {

	public static long journalizeCount = 0;
	
	public void journaliseEvent(byte[] marshalledObject) {
		journalizeCount++;	
	}

	
	
}
