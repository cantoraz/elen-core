/**
 * 
 */
package cn.caz.elen.core.filefactory;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 文件路径工具类，用于生成文件名，映射本地、远程路径。
 * <p>
 * <b>约定</b>
 * <ul>
 * <li>凡目录, 末尾皆含目录分割符</li>
 * <li>由文件名前两个字符决定其二级子目录(即相对路径), 如在Unix中:
 * <tt>e104f5ed-3fc0-4fcb-9148-05c8c5882a20.jpg<tt> 会保存为 <tt>e/e1/e104f5ed-3fc0-4fcb-9148-05c8c5882a20.jpg</tt> ,
 * 相对路径以上的路径具体由 {@link PathConfig} 决定</li>
 * </ul>
 * 
 * @author Cantoraz Zhou
 * @version 2.0
 */
public class FileFactory {

	public static final char URL_SEPARATOR = '/';

	public static final char EXTENSION_SEPARATOR = '.';

	private Map<String, PathConfig> map = null;

	/**
	 * @return the map
	 */
	public Map<String, PathConfig> getMap() {
		return this.map;
	}

	/**
	 * @param map
	 *            the map to set
	 */
	public void setMap(Map<String, PathConfig> map) {
		this.map = map;
	}

	/**
	 * 获取 PathConfig
	 * 
	 * @param flag
	 * @return
	 */
	public PathConfig getPathConfig(IPathFlag flag) {
		if (flag == null)
			return null;

		if (this.map == null)
			return null;

		return this.map.get(flag.getFlag());
	}

	/**
	 * 生成随机文件名(带扩展名)
	 * 
	 * @param fileExt
	 *            若无文件扩展名, 则传入null
	 * @return
	 */
	public String nextFileName(String fileExt) {
		StringBuilder sb = new StringBuilder();
		sb.append(UUID.randomUUID().toString());

		if (!StringUtils.isEmpty(fileExt))
			sb.append(EXTENSION_SEPARATOR).append(fileExt);

		return sb.toString();
	}

	/**
	 * 映射文件的路径
	 * 
	 * @param pathType
	 * @param flag
	 * @param fileName
	 * @param withFileName
	 * @return
	 */
	public String mapPath(PathType pathType, IPathFlag flag, String fileName, boolean withFileName) {
		switch (pathType) {
		case HTTP:
			return mapHttpPath(flag, fileName, withFileName);
		case LOCAL:
		default:
			return mapLocalPath(flag, fileName, withFileName);
		}
	}

	/**
	 * 映射文件的本地文件系统路径
	 * 
	 * @param flag
	 * @param fileName
	 * @param withFileName
	 * @return
	 */
	public String mapLocalPath(IPathFlag flag, String fileName, boolean withFileName) {
		String fname = FilenameUtils.getName(fileName);

		if (flag == null || StringUtils.isEmpty(fname))
			return null;

		PathConfig pc = getPathConfig(flag);

		if (pc == null)
			return null;

		String relativePath = mapRelativePath(PathType.LOCAL, fname, withFileName);

		if (relativePath == null)
			return null;

		StringBuilder sb = new StringBuilder();
		sb.append(pc.getLocalPath()).append(relativePath);

		return sb.toString();
	}

	/**
	 * 映射文件的网络路径
	 * 
	 * @param flag
	 * @param fileName
	 * @param withFileName
	 * @return
	 */
	public String mapHttpPath(IPathFlag flag, String fileName, boolean withFileName) {
		String fName = FilenameUtils.getName(fileName);

		if (flag == null || StringUtils.isEmpty(fName))
			return null;

		PathConfig pc = getPathConfig(flag);

		if (pc == null)
			return null;

		String relativePath = mapRelativePath(PathType.HTTP, fName, withFileName);

		if (relativePath == null)
			return null;

		StringBuilder sb = new StringBuilder();
		sb.append(pc.getHttpPath()).append(relativePath);

		return sb.toString();
	}

	/**
	 * 映射文件的相对路径
	 * 
	 * @param pathType
	 * @param fileName
	 * @param withFileName
	 * @return
	 */
	public String mapRelativePath(PathType pathType, String fileName, boolean withFileName) {
		switch (pathType) {
		case HTTP:
			return mapRelativeHttpPath(fileName, withFileName);
		case LOCAL:
		default:
			return mapRelativeLocalPath(fileName, withFileName);
		}
	}

	/**
	 * 映射文件的本地相对路径
	 * 
	 * @param fileName
	 * @param witdhFileName
	 * @return
	 */
	public String mapRelativeLocalPath(String fileName, boolean witdhFileName) {
		String fName = FilenameUtils.getName(fileName);

		if (StringUtils.isEmpty(fName))
			return null;

		StringBuilder sb = new StringBuilder();

		try {
			sb.append(getRelativeDir(PathType.LOCAL, fName));
			if (witdhFileName) {
				sb.append(fName);
			}
		} catch (Exception e) {
			return null;
		}

		return sb.toString();
	}

	/**
	 * 映射文件的网络相对路径
	 * 
	 * @param fileName
	 * @param withFileName
	 * @return
	 */
	public String mapRelativeHttpPath(String fileName, boolean withFileName) {
		String fName = FilenameUtils.getName(fileName);

		if (StringUtils.isEmpty(fName))
			return null;

		StringBuilder sb = new StringBuilder();

		try {
			sb.append(getRelativeDir(PathType.HTTP, fName));
			if (withFileName) {
				sb.append(fName);
			}
		} catch (Exception e) {
			return null;
		}

		return sb.toString();
	}

	/**
	 * 内部工具方法. 根据给定文件名, 获取两级子目录, 即相对路径
	 * 
	 * @param pathType
	 * @param fileName
	 * @return
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	private String getRelativeDir(PathType pathType, String fileName) throws NullPointerException,
			IllegalArgumentException {
		if (pathType == null)
			throw new NullPointerException("Unexpected null pathType!");

		if (StringUtils.isEmpty(fileName))
			throw new IllegalArgumentException("Unexpected empty fileName!");

		if (FilenameUtils.getBaseName(fileName).length() < 3) {
			throw new IllegalArgumentException("Unexpected too short fileName!");
		}

		Character separator = null;
		switch (pathType) {
		case HTTP:
			separator = URL_SEPARATOR;
			break;
		case LOCAL:
		default:
			separator = File.separatorChar;
			break;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(fileName.substring(0, 1)).append(separator).append(fileName.substring(0, 2)).append(separator);

		return sb.toString();
	}

}
