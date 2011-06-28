/**
 * 
 */
package cn.caz.elen.core.filefactory;

/**
 * 核心路径配置标识枚举
 * <p>
 * 方法 getFlag() 所返回应保持与 properties 中定义一致
 * 
 * @author Cantoraz Zhou
 * 
 */
public enum PathFlag implements IPathFlag {

	IMAGE,
	IMAGEORIGIN,
	VIDEO,
	VIDEOORIGIN;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.dskb.util.filefactory.IPathFlag#getFlag()
	 */
	@Override
	public String getFlag() {
		return this.name();
	}
}
