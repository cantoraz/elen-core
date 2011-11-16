/**
 * 
 */
package cn.caz.test;

import org.junit.Test;

import cn.caz.elen.core.AbstractTest;

/**
 * @author caz
 * 
 */
public class StringArrayTest extends AbstractTest {

	@Test
	public void test() {
		String[][] arr = new String[10][3];

		logger.debug(arr.length);
		logger.debug(arr[0].length);
		logger.debug(arr[9][0]);
	}

}
