package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;

	public void upload(BlogVo blogVo) {
		blogRepository.upload(blogVo);
		
	}

	public BlogVo findFileName(BlogVo blogVo) {
		BlogVo vo = blogRepository.findFileName(blogVo);
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
 
}
