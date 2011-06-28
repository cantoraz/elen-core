/**
 * 
 */
package cn.caz.elen.core.filefactory;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

/**
 * 路径配置对象, 用于 FileFactory
 * <p>
 * <b>约定</b>
 * <ul>
 * <li>凡目录, 末尾皆含目录分割符</li>
 * </ul>
 * 
 * @author Cantoraz Zhou
 * @version 1.1
 */
public class PathConfig {

	/**
	 * 标识符
	 */
	private String flag;

	/**
	 * 本地路径
	 */
	private String localPath;

	/**
	 * 网络路径
	 */
	private String httpPath;

	/**
	 * default constructor
	 */
	public PathConfig() {
		super();
	}

	/**
	 * @param flag
	 * @param localPath
	 * @param httpPath
	 */
	public PathConfig(String flag, String localPath, String httpPath) {
		this();
		this.setFlag(flag);
		this.setLocalPath(localPath);
		this.setHttpPath(httpPath);
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the localPath
	 */
	public String getLocalPath() {
		return this.localPath;
	}

	/**
	 * @param localPath
	 *            the localPath to set
	 */
	public void setLocalPath(String localPath) {
		this.localPath = FilenameUtils.normalizeNoEndSeparator(localPath);
		if (!this.localPath.endsWith(File.separator))
			this.localPath += File.separator;
	}

	/**
	 * @return the httpPath
	 */
	public String getHttpPath() {
		return this.httpPath;
	}

	/**
	 * @param _httpPath
	 *            the httpPath to set
	 */
	public void setHttpPath(String _httpPath) {
		this.httpPath = FilenameUtils.separatorsToUnix(FilenameUtils.normalizeNoEndSeparator(_httpPath));
		if (!this.httpPath.endsWith(File.separator))
			this.httpPath += FileFactory.URL_SEPARATOR;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("PathConfig={").append("flag:'").append(this.flag).append("',").append("localPath:'")
				.append(this.localPath).append("',").append("httpPath:'").append(this.httpPath).append("'}");
		return sb.toString();
	}
}
