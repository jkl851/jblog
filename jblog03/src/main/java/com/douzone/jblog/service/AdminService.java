package com.douzone.jblog.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.AdminRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;

	public List<CategoryVo> getCategoryPostCounts(String blogId) {
		return adminRepository.getCategoryPostCounts(blogId);
	}
	
	public List<BlogVo> getCategories(String blogId) {
		return adminRepository.getCategories(blogId);
	}
	
	public List<CategoryVo> getCategoriesAjax(String blogId) {
		return adminRepository.getCategoriesAjax(blogId);
	}
	
	public CategoryVo getCategoryAjax(String blogId) {
		return adminRepository.getCategoryAjax(blogId);
	}
	
	public List<PostVo> getLatestPost(String blogId, Long categoryNo) {
		CategoryVo vo = new CategoryVo();
		vo.setBlog_id(blogId);
		vo.setNo(categoryNo);
		return adminRepository.getLatestPost(vo);
	}
	
	public Boolean categoryDel(Long no) {
		return adminRepository.categoryDel(no);
	}
	
	public void categoryAdd(CategoryVo vo) {
		adminRepository.categoryAdd(vo);
	}
	
	public Boolean writePost(String blogId, String categoryName, String title, String contents) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "blogId", blogId);
		map.put( "categoryName", categoryName);
		map.put( "title", title);
		map.put( "contents", contents);
		return adminRepository.writePost(map);
	}
}