/**
 * 
 */
package cn.caz.test;

import java.io.IOException;
import java.net.MalformedURLException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import cn.caz.elen.core.AbstractTest;

/**
 * @author caz
 * 
 */
public class XMLTest extends AbstractTest {

	@Test
	public void test() {
		try {
			Document doc = new SAXReader(true).read(applicationContext.getResource("filefactory.cfg.xml").getURL());
			System.out.println(doc.asXML());
		} catch (MalformedURLException murle) {
			logger.error("Failed to init " + this.getClass().getSimpleName(), murle);
		} catch (DocumentException de) {
			logger.error("Failed to init " + this.getClass().getSimpleName(), de);
		} catch (IOException ioe) {
			logger.error("Failed to init " + this.getClass().getSimpleName(), ioe);
		}
	}

}
