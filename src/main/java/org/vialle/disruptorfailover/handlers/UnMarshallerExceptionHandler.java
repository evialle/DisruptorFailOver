package org.vialle.disruptorfailover.handlers;

import org.vialle.disruptorfailover.services.MarshallerService;

import com.lmax.disruptor.ExceptionHandler;

public class UnMarshallerExceptionHandler implements ExceptionHandler {

	public UnMarshallerExceptionHandler(MarshallerService marshallerService) {
		// TODO Auto-generated constructor stub
	}

	public void handleEventException(Throwable ex, long sequence, Object event) {
		System.out.println(ex + " - " + sequence + " - " + event);

	}

	public void handleOnStartException(Throwable ex) {
		// TODO Auto-generated method stub
		System.out.println(ex);
		
	}

	public void handleOnShutdownException(Throwable ex) {
		// TODO Auto-generated method stub
		System.out.println(ex);

	}

}
