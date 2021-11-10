package com.douzone.jblog.vo;

public class PostVo {

	private Long catNo;
	private String catName;
	private Long postNo;
	private String title;
	private String contents;
	private String reg_date;
	private Long postCounts;
	private String blog_id;
	
	public Long getCatNo() {
		return catNo;
	}
	public void setCatNo(Long catNo) {
		this.catNo = catNo;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public Long getPostNo() {
		return postNo;
	}
	public void setPostNo(Long postNo) {
		this.postNo = postNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public Long getPostCounts() {
		return postCounts;
	}
	public void setPostCounts(Long postCounts) {
		this.postCounts = postCounts;
	}
	public String getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}
	@Override
	public String toString() {
		return "PostVo [catNo=" + catNo + ", catName=" + catName + ", postNo=" + postNo + ", title=" + title
				+ ", contents=" + contents + ", reg_date=" + reg_date + ", postCounts=" + postCounts + ", blog_id="
				+ blog_id + "]";
	}
}
