/**
 * 
 */
package org.vialle.disruptorfailover;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.vialle.disruptorfailover.services.JournalisationService;
import org.vialle.disruptorfailover.services.MarshallerService;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.ClaimStrategy;
import com.lmax.disruptor.MultiThreadedClaimStrategy;
import com.lmax.disruptor.WaitStrategy;

/**
 * @author Eric
 * 
 */
public class ConfigDisruptor {
	
	private JournalisationService journalisationService;
	private MarshallerService marshallerService;
	private ExecutorService executorService;
	private int ringSize = 64;
	private WaitStrategy waitingStrategy;
	private ClaimStrategy claimStrategy;
	
	public static ConfigDisruptor createConfig() {
		return new ConfigDisruptor();
	}
	
	public DisruptorService createDisruptorService() throws FileNotFoundException {
		
		DisruptorService ds = new DisruptorService(this);
		
		return ds;
	}
	
	public ConfigDisruptor setExecutorService(final ExecutorService execService) {
		this.executorService = execService;
		return this;
	}
	
	public ExecutorService getExecutorService() {
		if (this.executorService == null) {
			this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		}
		
		return this.executorService;
		
	}
	
	public ConfigDisruptor setRingSize(int ringSize) {
		this.ringSize = ringSize;
		return this;
	}
	
	public int getRingSize() {
		return this.ringSize;
	}
	
	public ConfigDisruptor setJournalisationService(final JournalisationService jourService) {
		this.journalisationService = jourService;
		return this;
	}
	
	/**
	 * @return the journalisationService
	 */
	public JournalisationService getJournalisationService() {
		return this.journalisationService;
	}
	
	public ConfigDisruptor setMarshallerService(final MarshallerService marshService) {
		this.marshallerService = marshService;
		return this;
	}
	
	/**
	 * @return the marshallerService
	 */
	public MarshallerService getMarshallerService() {
		return this.marshallerService;
	}

	public WaitStrategy getWaitingStrategy() {
		
		if (this.waitingStrategy == null) {
			this.waitingStrategy = new BusySpinWaitStrategy();
		}
		
		return waitingStrategy ;
	}
	
	public ClaimStrategy getClaimStrategy() {
		
		if (this.claimStrategy == null) {
			this.claimStrategy = new MultiThreadedClaimStrategy(getRingSize());
		}
		return this.claimStrategy;
		
	}
	
}
