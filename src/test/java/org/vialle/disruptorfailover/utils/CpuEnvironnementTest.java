/**
 * 
 */
package org.vialle.disruptorfailover.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Eric
 *
 */
public class CpuEnvironnementTest {
	
	@Test
	public void testAvailableCpu() {
		int nbCores = CpuEnvironnement.getNbCores();
		
		Assert.assertEquals(4, nbCores);
	}
	
}
