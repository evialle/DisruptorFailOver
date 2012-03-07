/**
 * 
 */
package org.vialle.disruptorfailover.utils;

/**
 * @author Eric
 *
 */
public class CpuEnvironnement {
	
	private static int availableProcessors = Runtime.getRuntime().availableProcessors();

	public static int getNbCores() {
		return availableProcessors;
		
	}
	
}
