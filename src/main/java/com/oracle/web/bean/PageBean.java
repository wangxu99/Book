package com.oracle.web.bean;

import java.util.List;
 
public class PageBean<T> {
	 private Integer pageNow; 
	 
	    private int counts;  
	    private int pageSize; 
	    private List<T> beanList;
	    private String url;
	    
		public PageBean(Integer pageNow, int counts, int pageSize, List<T> beanList, String url) {
			super();
			this.pageNow = pageNow;
			this.counts = counts;
			this.pageSize = pageSize;
			this.beanList = beanList;
			this.url = url;
		}

		public int getPages() {
			int pages=this.counts/this.pageSize;
			return (this.counts%this.pageSize==0)?pages:pages+1;
		}
		
		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public Integer getPageNow() {
			return pageNow;
		}

		public void setPageNow(Integer pageNow) {
			this.pageNow = pageNow;
		} 
		public int getCounts() {
			return counts;
		}
		public void setCounts(int counts) {
			this.counts = counts;
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public List<T> getBeanList() {
			return beanList;
		}
		public void setBeanList(List<T> beanList) {
			this.beanList = beanList;
		}
		public PageBean() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "PageBean [pageNow=" + pageNow + ", counts=" + counts + ", pageSize=" + pageSize + ", beanList="
					+ beanList + ", url=" + url + "]";
		}
	
 
	 
	 
	     
}
