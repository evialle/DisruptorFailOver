package org.vialle.disruptorfailover.services;

public interface JournalisationService {

	void journaliseEvent(byte[] marshalledObject);

}
