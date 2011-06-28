/**
 * 
 */
package cn.caz.elen.core;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Cantoraz Zhou
 * 
 */
public class HelloWorldTest extends AbstractTest {

	@Autowired
	private HelloWorld helloWorld;

	@Test
	public void testAutowired() {
		assertNotNull(this.helloWorld);
	}

	@Test
	public void testSayHello() {
		String result = this.helloWorld.sayHello();

		assertEquals("Hello Maven", result);
	}

}
