package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;

	public void upload(BlogVo blogVo) {
		blogRepository.upload(blogVo);
		
	}

	public BlogVo findFileName(String id) {
		BlogVo vo = blogRepository.findFileName(id);
		return vo;
	}

	public List<CategoryVo> categoryGet(CategoryVo categoryVo) {
		List<CategoryVo> getValues = (List<CategoryVo>) blogRepository.categoryGet(categoryVo); 
		return getValues;
	}

	public void categoryInsert(CategoryVo categoryVo) {
		blogRepository.categoryInsert(categoryVo);
		
	}

	public void categoryDelete(CategoryVo categoryVo) {
		blogRepository.categoryDelete(categoryVo);
	}

	public List<CategoryVo> getCategoryName() {
		List<CategoryVo> categoryNames = blogRepository.getCategoryName();
		return categoryNames;
	}

	public void writeInsert(PostVo postVo) {
		blogRepository.writeInsert(postVo);
		
	}

	public List<CategoryVo> categoryPostCount(String id) {
		List<CategoryVo> postCounts = blogRepository.categoryPostCount(id);
		 return postCounts;
	}
 
}
