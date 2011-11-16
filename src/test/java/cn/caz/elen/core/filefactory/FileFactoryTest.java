/**
 * 
 */
package cn.caz.elen.core.filefactory;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.caz.elen.core.AbstractTest;

/**
 * @author Cantoraz Zhou
 * 
 */
public class FileFactoryTest extends AbstractTest {

	@Autowired
	private FileFactory fileFactory;

	@Test
	public void testFileFactory() {
		Assert.assertNotNull(fileFactory);
	}

	@Test
	public void testGetMap() {
		Assert.assertNotNull(fileFactory.getMap());
		Assert.assertTrue(fileFactory.getMap().size() > 0);
		logger.debug(fileFactory.getMap().size());
	}

	@Test
	public void testGetPathConfig() {
		PathConfig pc = fileFactory.getPathConfig(PathFlag.IMAGE);
		Assert.assertNotNull(pc);
		logger.debug(pc.getFlag());
		logger.debug(pc.getLocalPath());
		logger.debug(pc.getHttpPath());
	}

	@Test
	public void testNextFileName() {
		Assert.assertNotNull(fileFactory.nextFileName("jpg"));
	}

	@Test
	public void testMapPath() {
		String fileName = fileFactory.nextFileName("jpg");
		String path = fileFactory.mapPath(PathType.LOCAL, PathFlag.IMAGE, fileName, true);
		Assert.assertNotNull(path);
		logger.debug(path);
	}

	@Test
	public void testMapLocalPath() {
		String fileName = fileFactory.nextFileName("jpg");
		String path = fileFactory.mapLocalPath(PathFlag.IMAGE, fileName, false);
		Assert.assertNotNull(path);
		logger.debug(path);
	}

	@Test
	public void testMapHttpPath() {
		String fileName = fileFactory.nextFileName("jpg");
		String path = fileFactory.mapHttpPath(PathFlag.IMAGE, fileName, true);
		Assert.assertNotNull(path);
		logger.debug(path);
	}

	@Test
	public void testMapRelativePath() {
		String fileName = fileFactory.nextFileName("jpg");
		String path = fileFactory.mapRelativePath(PathType.LOCAL, fileName, true);
		Assert.assertNotNull(path);
		logger.debug(path);
	}

	@Test
	public void testMapRelativeLocalPath() {
		String fileName = fileFactory.nextFileName("jpg");
		String path = fileFactory.mapRelativeLocalPath(fileName, false);
		Assert.assertNotNull(path);
		logger.debug(path);
	}

	@Test
	public void testMapRelativeHttpPath() {
		String fileName = fileFactory.nextFileName("jpg");
		String path = fileFactory.mapRelativeHttpPath(fileName, true);
		Assert.assertNotNull(path);
		logger.debug(path);
	}
}
