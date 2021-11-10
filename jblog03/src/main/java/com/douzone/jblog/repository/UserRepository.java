package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.exception.UserRepositoryException;
import com.douzone.jblog.vo.UserVo;
@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession;	
	
	public UserVo findByIdAndPassword(
			String id, 
			String password) throws UserRepositoryException {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pw", password);
		
		return sqlSession.selectOne("user.findByIdAndPassword", map);
	}
	
	public boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);
		sqlSession.insert("user.insertDefault", vo);
	      return count == 1;
	}
}
