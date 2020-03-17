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
		System.out.println("2" + userVo);
		sqlSession.insert("user.join", userVo);
	}
}
