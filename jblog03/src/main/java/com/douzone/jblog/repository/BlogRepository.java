package com.douzone.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(BlogVo vo) {
		return 1 == sqlSession.update("site.insert", vo);
	}
	
	public Boolean insert(Map<String, Object> map) {
		return 1 == sqlSession.update("site.insertOnlyTitle", map);
	}
	
	public BlogVo getImageTitle(String blogId) {
		return sqlSession.selectOne("site.getImageTitle", blogId);
	}
	
	public List<PostVo> getPostsAndCategories(String blogId) {
		return sqlSession.selectList("post.getPostsAndCategories", blogId);
	}
	
	public List<PostVo> getLatestPost(String blogId) {
		return sqlSession.selectList("post.getLatestPost", blogId);
	}
	
	public PostVo getPost(PostVo vo) {
		return sqlSession.selectOne("post.getPost", vo);
	}
	
	public List<CategoryVo> getCategories(String blogId) {
		return sqlSession.selectList("post.getCategories", blogId);
	}
	
}
