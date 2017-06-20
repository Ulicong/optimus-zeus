package com.common.util;

import com.brojade.pub.plugin.PageList;

public class JsonResult {

	// 成功标志
	private Boolean success;

	// 错误码
	private String errorCode;

	// 返回的数据对象
	private Object data;

	// 错误信息
	private String errorMsg;

	/** 分页信息 */

	// 当前页
	private Integer currentPage;

	// 每页显示数量
	private Integer pageSize;

	// 总页数
	private Integer totalPage;

	// 总记录数
	private Integer totalSize;

	// 是否有下一页
	private Boolean hasNext;

	// 是否有上一页
	private Boolean hasPre;

	public JsonResult() {
	}

	public JsonResult(Object data) {
		if (data != null) {
			if (data instanceof PageList<?>) {
				PageList<?> list = (PageList<?>) data;
				this.currentPage = list.getCurrentPage();
				this.pageSize = list.getPageSize();
				this.totalPage = list.getTotalPage();
				this.totalSize = list.getTotalSize();
				this.hasNext = list.getHasNext();
				this.hasPre = list.getHasPre();

				this.data = data;
			} else {
				this.data = data;
			}
		}
		this.success = true;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}

	public Boolean getHasNext() {
		return hasNext;
	}

	public void setHasNext(Boolean hasNext) {
		this.hasNext = hasNext;
	}

	public Boolean getHasPre() {
		return hasPre;
	}

	public void setHasPre(Boolean hasPre) {
		this.hasPre = hasPre;
	}

}
