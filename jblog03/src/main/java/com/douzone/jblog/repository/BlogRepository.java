package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class BlogRepository {
	
	@Autowired
	private SqlSession sqlSession; 
	public void insert(UserVo userVo) {
		sqlSession.insert("blog.insert", userVo);
	}
	
	public void upload(BlogVo blogVo) {
		sqlSession.insert("blog.upload", blogVo);
	}
	
//	public int write(PostVo postVo) {
//		
//		return sqlSession.insert("category.write", postVo);
//	}


}
