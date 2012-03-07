package org.vialle.disruptorfailover;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventTranslator;

/**
 * 
 */

/**
 * @author Eric
 * 
 */
public class NetworkEvent implements EventTranslator<NetworkEvent> {

	
	private byte[] marshalledObject;

	public static final EventFactory<NetworkEvent> FACTORY = new EventFactory<NetworkEvent>() {
		public NetworkEvent newInstance() {
			return new NetworkEvent();
		}
	};

	//Check the implementation
	public NetworkEvent translateTo(NetworkEvent event, long sequence) {
		this.setMarshalledObject(event.getMarshalledObject());
		return this;
	}

	/**
	 * @return the marshalledObject
	 */
	public byte[] getMarshalledObject() {
		return marshalledObject;
	}

	/**
	 * @param marshalledObject the marshalledObject to set
	 */
	public void setMarshalledObject(byte[] marshalledObject) {
		this.marshalledObject = marshalledObject;
	}

}
