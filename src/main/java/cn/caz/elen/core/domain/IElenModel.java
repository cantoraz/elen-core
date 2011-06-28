/**
 * 
 */
package cn.caz.elen.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Cantoraz Zhou
 * 
 */
public interface IElenModel extends Serializable {

	public abstract Serializable getId();

	public abstract Date getCreateTime();

	public abstract Date getModifyTime();
}
