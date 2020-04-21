package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class BlogRepository {
	
	@Autowired
	private SqlSession sqlSession; 
//	public void insert(UserVo userVo) {
//		sqlSession.insert("blog.insert", userVo);
//	}
	
	public void upload(BlogVo blogVo) {
		sqlSession.insert("blog.upload", blogVo);
	}

//	public BlogVo findFileName(String id) {
//		BlogVo vo = sqlSession.selectOne("blog.findFileName", id);
//		return vo;
//	}

	public BlogVo getBlogValue(BlogVo blogVo) {
		BlogVo vo = sqlSession.selectOne("blog.getBlogValue", blogVo);
		return vo;
	}


	public List<CategoryVo> categoryGet(CategoryVo categoryVo) {
		List<CategoryVo> getValues = sqlSession.selectList("category.categoryGet", categoryVo);
		return getValues;
	}

	public void categoryInsert(CategoryVo categoryVo) {
		sqlSession.insert("category.categoryInsert", categoryVo);
		
	}

	public int categoryDelete(Long no) {
		return sqlSession.delete("category.categoryDelete", no);
		
	}

	public List<CategoryVo> getCategoryName() {
		List<CategoryVo> categoryNames = sqlSession.selectList("post.getCategoryName");
		return categoryNames;
	}

	public void writeInsert(PostVo postVo) {
		sqlSession.insert("post.writeInsert", postVo);
		
	}

	public List<CategoryVo> getCateValueById(String id) {
		List<CategoryVo> postCounts = sqlSession.selectList("category.getCateValueById", id);
		return postCounts;
	}

	public PostVo getCategoryPost(HashMap<String, Object> map) {
		return sqlSession.selectOne("blog.getcategorypost", map);
	}

	public List<PostVo> getCategoryPostList(HashMap<String, Object> map) {
		return sqlSession.selectList("blog.getcategorypostlist", map);
	}

	public PostVo getCategoryMainPost(HashMap<String, Object> setMap) {
		return sqlSession.selectOne("blog.getcategorymainpost", setMap);
	}

	public PostVo getBlogMainPost(UserVo vo) {
		return sqlSession.selectOne("blog.getblogmainpost", vo);
	}
	
	public List<PostVo> getBlogMainPostList(UserVo vo) {
		return sqlSession.selectList("blog.getblogmainpostlist", vo);
	}

	public List<CategoryVo> findAllById(String id) {
		return sqlSession.selectList("category.findAllById", id);
	}

//	public List<BlogVo> findAllByNo(Long startNo) {
//		return sqlSession.selectList("blog.findAllByNo", startNo);
//		
//	}
	
}
