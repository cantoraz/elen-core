/**
 * 
 */
package cn.caz.elen.core.vo;

/**
 * @author Cantoraz Zhou
 * 
 */
public class Pager {

	public static final int MAX_RESULTS_PER_PAGE = 100;

	private int currPage = 1;

	private int totalPages = 0;

	private int maxResults = 10;

	private long totalResults = 0l;

	/**
	 * 
	 */
	public Pager() {
	}

	/**
	 * @param maxResults
	 */
	public Pager(int maxResults) {
		this.maxResults = maxResults;
	}

	/**
	 * @param currPage
	 * @param maxResults
	 */
	public Pager(int currPage, int maxResults) {
		this.currPage = currPage;
		this.maxResults = maxResults;
	}

	/**
	 * @return the currPage
	 */
	public int getCurrPage() {
		return this.currPage;
	}

	/**
	 * @param currPage
	 *            the currPage to set
	 */
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return this.totalPages;
	}

	/**
	 * @param totalPages
	 *            the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return the maxResults
	 */
	public int getMaxResults() {
		return this.maxResults;
	}

	/**
	 * @param maxResults
	 *            the maxResults to set
	 */
	public void setMaxResults(int maxResults) {
		if (maxResults > 0) {
			this.maxResults = (maxResults < MAX_RESULTS_PER_PAGE) ? maxResults : MAX_RESULTS_PER_PAGE;
		}
	}

	/**
	 * @return the totalResults
	 */
	public long getTotalResults() {
		return this.totalResults;
	}

	/**
	 * @param totalResults
	 *            the totalResults to set
	 */
	public void setTotalResults(long totalResults) {
		this.totalResults = totalResults;

		if (this.totalPages == 0) {
			if (this.totalResults == 0) {
				this.totalPages = 1;
			} else if (this.totalResults > 0) {
				int pages = (int) (this.totalResults / this.maxResults);
				this.totalPages = ((this.totalResults % this.maxResults) == 0) ? pages : pages + 1;
			}
		}
	}

	public int getFirstResult() {
		return (this.currPage - 1) * this.maxResults;
	}

}
