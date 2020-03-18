package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;

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

//	public String findFileName(BlogVo blogVo) {
//		String test1 = blogRepository.findFileName(blogVo);
//		 System.out.println("test1 " + test1);
//		 return test1;
//		
//	}


//	public int write(PostVo postVo) {
//		
//		return blogRepository.write(postVo);
//	}
}
