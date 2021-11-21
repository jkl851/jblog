package com.douzone.jblog.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Repository
public class AdminRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<CategoryVo> getCategoryPostCounts(String blogId) {
		return sqlSession.selectList("site.getCategoryPostCounts", blogId);
	}
	
	public List<BlogVo> getCategories(String blogId) {
		return sqlSession.selectList("site.getCategories", blogId);
	}
	
	public List<CategoryVo> getCategoriesAjax(String blogId) {
		return sqlSession.selectList("post.getCategories", blogId);
	}
	
	public CategoryVo getCategoryAjax(String blogId) {
		return sqlSession.selectOne("post.getCategory", blogId);
	}
	
	public List<PostVo> getLatestPost(CategoryVo vo) {
		return sqlSession.selectList("post.getPostByCat", vo);
	}
	
	public Boolean categoryDel(Long no) {
		return 1 == sqlSession.delete("site.categoryDel", no);
	}
	
	public void categoryAdd(CategoryVo vo) {
		sqlSession.insert("site.categoryAdd", vo);
	}
	
	public Boolean writePost(Map<String, Object> map) {
		return 1 == sqlSession.insert("site.writePost", map);
	}
	
}