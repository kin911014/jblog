package com.douzone.jblog.service;

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

	public CategoryVo categoryGet(CategoryVo categoryVo) {
		CategoryVo getValue = blogRepository.categoryGet(categoryVo); 
		return getValue;
	}


//	public int write(PostVo postVo) {
//		
//		return blogRepository.write(postVo);
//	}
}
