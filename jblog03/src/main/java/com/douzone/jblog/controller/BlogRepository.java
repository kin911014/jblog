package com.douzone.jblog.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class BlogRepository {
	
	@Autowired
	private SqlSession sqlSession; 
//	public int write(PostVo postVo) {
//		
//		return sqlSession.insert("category.write", postVo);
//	}

}
