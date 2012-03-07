package org.vialle.disruptorfailover.services;

public interface MarshallerService {

	public Object unMarshal(byte[] marshalledObject);
	
	public void marshal(final Object object);

}
