package com.douzone.jblog.vo;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class BlogVo {
	@NotEmpty
	@Length(min=4, max=16)
	private String id;
	private String title;
	private String logo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Override
	public String toString() {
		return "BlogVo [id=" + id + ", title=" + title + ", logo=" + logo + "]";
	}

}
