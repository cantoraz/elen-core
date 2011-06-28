/**
 * 
 */
package cn.caz.elen.core.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Cantoraz Zhou
 * 
 */
@MappedSuperclass
public abstract class ElenModel implements IElenModel {

	private static final long serialVersionUID = -7676964896682098211L;

	/**
	 * Primary key
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Create time
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = Calendar.getInstance().getTime();

	/**
	 * Modify time
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyTime = Calendar.getInstance().getTime();

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.domain.IElenModel#getId()
	 */
	public Serializable getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.domain.IElenTimeAwareModel#getCreateTime()
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.domain.IElenTimeAwareModel#getModifyTime()
	 */
	public Date getModifyTime() {
		return this.modifyTime;
	}

	/**
	 * @param modifyTime
	 *            the modifyTime to set
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}
