package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession; 
	
	public void join(UserVo userVo) {
		sqlSession.insert("user.join", userVo);
	}

	public UserVo findByEmailAndPassword(UserVo userVo) {
		
		return sqlSession.selectOne("user.findByEmailAndPassword", userVo);
		
	}
}
