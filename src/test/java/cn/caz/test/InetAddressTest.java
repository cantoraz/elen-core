/**
 * 
 */
package cn.caz.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

import cn.caz.elen.core.AbstractTest;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

/**
 * @author caz
 * 
 */
@SuppressWarnings("restriction")
public class InetAddressTest extends AbstractTest {

	@Test
	public void test() {
		try {
			InetAddress addr = InetAddress.getLocalHost();

			logger.debug("canonical=" + addr.getCanonicalHostName());
			logger.debug("hostaddr=" + addr.getHostAddress());
			logger.debug("hostname=" + addr.getHostName());

			byte[] bAddress = addr.getAddress();
			logger.debug("address=" + HexBin.encode(bAddress));

			StringBuffer sbAddress = new StringBuffer();
			for (byte b : bAddress) {
				sbAddress.append(Integer.toHexString(((int) b) & 0x000000FF));
			}
			logger.debug("address=" + sbAddress.toString());
			logger.debug(Integer.toHexString((bAddress[bAddress.length - 1]) & 0x000000FF));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
