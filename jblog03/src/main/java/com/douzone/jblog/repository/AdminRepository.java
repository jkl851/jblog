package com.douzone.jblog.repository;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;

@Repository
public class AdminRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<BlogVo> getCategoryPostCounts(String blogId) {
		return sqlSession.selectList("site.getCategoryPostCounts", blogId);
	}
	
	public List<BlogVo> getCategories(String blogId) {
		return sqlSession.selectList("site.getCategories", blogId);
	}
	
	public Boolean categoryDel(Long no) {
		return 1 == sqlSession.delete("site.categoryDel", no);
	}
	
	public Boolean categoryAdd(CategoryVo vo) {
		return 1 == sqlSession.insert("site.categoryAdd", vo);
	}
	
	public Boolean writePost(Map<String, Object> map) {
		return 1 == sqlSession.insert("site.writePost", map);
	}
	
}