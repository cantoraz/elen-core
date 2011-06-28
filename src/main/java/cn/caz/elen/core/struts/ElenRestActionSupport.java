/**
 * 
 */
package cn.caz.elen.core.struts;

import org.apache.struts2.rest.RestActionSupport;

/**
 * @author Cantoraz Zhou
 * @version 1.0
 * 
 */
public abstract class ElenRestActionSupport extends RestActionSupport {

	private static final long serialVersionUID = -7203474269043783522L;

	public static final String INDEX = "index";
	public static final String SHOW = "show";
	public static final String CREATE = "create";
	public static final String UPDATE = "update";
	public static final String DESTROY = "destroy";
	public static final String EDIT = "edit";
	public static final String EDITNEW = "editNew";

}
