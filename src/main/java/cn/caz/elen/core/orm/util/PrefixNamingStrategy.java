/**
 * 
 */
package cn.caz.elen.core.orm.util;

import org.apache.commons.lang.StringUtils;
import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * @author Cantoraz Zhou
 * 
 */
public class PrefixNamingStrategy extends ImprovedNamingStrategy {

	private static final long serialVersionUID = 8220073278117337998L;

	private String prefix = StringUtils.EMPTY;

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return this.prefix;
	}

	/**
	 * @param prefix
	 *            the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.hibernate.cfg.ImprovedNamingStrategy#classToTableName(java
	 * .lang.String)
	 */
	@Override
	public String classToTableName(String className) {
		return this.prefix + super.classToTableName(className);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.hibernate.cfg.ImprovedNamingStrategy#logicalCollectionTableName
	 * (java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String logicalCollectionTableName(String tableName, String ownerEntityTable, String associatedEntityTable,
			String propertyName) {
		return this.prefix
				+ super.logicalCollectionTableName(tableName, ownerEntityTable, associatedEntityTable, propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.hibernate.cfg.ImprovedNamingStrategy#tableName(java.lang.
	 * String)
	 */
	@Override
	public String tableName(String tableName) {
		return this.prefix + super.tableName(tableName);
	}

}
