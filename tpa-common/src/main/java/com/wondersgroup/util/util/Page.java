package com.wondersgroup.util.util;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.util.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

/**
 * @Title Page.java
 * @Package com.teda.common
 * @Description 分页信息，以及查询结果保存
 * @author 陈希峰 chenxf10@126.com
 * @date 2012-4-15 下午5:57:21
 * @version V1.0
 */
@XmlRootElement
public class Page<T> {
	/** @Fields currentPage : 分页参数 */
	protected int currentPage = 1;
	/** @Fields sizeOfPerPage : 每页显示条数 */
	protected int sizeOfPerPage = 20;
	/** @Fields totalSize : 总记录数 */
	protected Long totalSize = -1l;
	/** @Fields orderBy : 排序字段,可能有多个 */
	protected String orderBy = null;
	/** @Fields sort : 排序方式 */
	protected String sort = "DESC";
	/** @Fields autoCount : 是否自动查看总记录数 */
	protected boolean autoCount = true;
	/** @Fields pageNum : 分页时显示的分页条数（最好是奇数） */
	/** @Fields results : 当前查询的结果集 */
	protected List<T> results = Collections.emptyList();

	// 构造函数 //
	public Page() {
		super();
	}

	/**
	 * @Title Page
	 * @author 陈希峰 chenxf10@126.com
	 * @Description 根据总记录数进行实例化
	 * @param totalSize
	 * @throws
	 */
	public Page(final Long totalSize) {
		setTotalSize(totalSize);
	}

	/**
	 * @Title Page
	 * @author 陈希峰 chenxf10@126.com
	 * @Description 根据总记录数和每页显示条数生成分页
	 * @param totalSize
	 * @param sizeOfPerPage
	 * @throws
	 */
	public Page(final Long totalSize, final int sizeOfPerPage) {
		setTotalSize(totalSize);
		setSizeOfPerPage(sizeOfPerPage);
	}

	/**
	 * @Title Page
	 * @author 陈希峰 chenxf10@126.com
	 * @param sizeOfPerPage
	 *            每页显示记录数
	 * @param autoCount
	 *            是否查询总记录数
	 * @throws
	 */
	public Page(final int sizeOfPerPage, final boolean autoCount) {
		setSizeOfPerPage(sizeOfPerPage);
		setAutoCount(autoCount);
	}

	/** @return currentPage */
	@JSONField(name = "page")
	public int getCurrentPage() {
		return currentPage;
	}

	/** @param currentPage */
	public void setCurrentPage(int currentPage) {
		if (currentPage < 1) {
			this.currentPage = 1;
		} else {
			this.currentPage = currentPage;
		}
	}

	/** @return sizeOfPerPage */
	public int getSizeOfPerPage() {
		return sizeOfPerPage;
	}

	/** @param sizeOfPerPage */
	public void setSizeOfPerPage(int sizeOfPerPage) {
		if (sizeOfPerPage < 1) {
			this.sizeOfPerPage = 1;
		} else {
			this.sizeOfPerPage = sizeOfPerPage;
		}
	}

	/** @return totalSize */
	@JSONField(name = "records")
	public long getTotalSize() {
		return totalSize;
	}

	/** @param totalSize */
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	/** @return orderBy */
	public String getOrderBy() {
		return orderBy;
	}

	/** @param orderBy */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/** @param totalSize */
	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}

	/** @return autoCount */
	public boolean isAutoCount() {
		return autoCount;
	}

	/** @param autoCount */
	public void setAutoCount(boolean autoCount) {
		this.autoCount = autoCount;
	}

	/** @return results */
	@JSONField(name = "rows")
	public List<T> getResults() {
		return results;
	}

	/** @param results */
	public void setResults(List<T> results) {
		this.results = results;
	}

	/**
	 * @Title getFirst
	 * @author 陈希峰 chenxf10@126.com
	 * @Description 根据currentPage和sizeOfPerPage计算当前页第一条记录在总结果集中的位置,序号从1开始
	 * @return int
	 * @throws
	 */
	public int getFirst() {
		return ((currentPage - 1) * sizeOfPerPage);
	}

	/**
	 * @Title isOrderBySetted
	 * @author 陈希峰 chenxf10@126.com
	 * @Description 是否已设置排序字段,无默认值
	 * @return
	 * @return boolean
	 * @throws
	 */
	public boolean isOrderBySetted() {
		return StringUtils.isEmpty(orderBy);
	}

	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为-1.
	 */

	/**
	 * @Title getTotalPages
	 * @author 陈希峰 chenxf10@126.com
	 * @Description 根据总记录数和每页条数计算总页数
	 * @return long
	 * @throws
	 */
	@JSONField(name = "total")
	public long getTotalPages() {
		if (totalSize < 0)
			return -1;
		long count = totalSize / sizeOfPerPage;
		if (totalSize % sizeOfPerPage > 0) {
			count++;
		}
		return count;
	}

	/**
	 * @Title isHasNext
	 * @author 陈希峰 chenxf10@126.com
	 * @Description 是否还有下一页.
	 * @return boolean
	 * @throws
	 */
	public boolean isHasNext() {
		return (currentPage + 1 <= getTotalPages());
	}

	/**
	 * @Title getNextPage
	 * @author 陈希峰 chenxf10@126.com
	 * @Description 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
	 * @return int
	 * @throws
	 */
	public int getNextPage() {
		if (isHasNext())
			return currentPage + 1;
		else
			return currentPage;
	}

	/**
	 * @Title isHasPre
	 * @author 陈希峰 chenxf10@126.com
	 * @Description 是否还有上一页.
	 * @return
	 * @return boolean
	 * @throws
	 */
	public boolean isHasPreviousPage() {
		return (currentPage - 1 >= 1);
	}

	/**
	 * @Title getPrePage
	 * @author 陈希峰 chenxf10@126.com
	 * @Description 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号.
	 * @return int
	 * @throws
	 */
	public int getPreviousPage() {
		if (isHasPreviousPage())
			return currentPage - 1;
		else
			return currentPage;
	}

	/** @return sort */
	public String getSort() {
		return sort;
	}

	/** @param sort */
	public void setSort(String sort) {
		this.sort = sort;
	}
}
