package com.douzone.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;

	public void upload(BlogVo blogVo) {
		blogRepository.upload(blogVo);
		
	}

//	public BlogVo findFileName(String id) {
//		BlogVo vo = blogRepository.findFileName(id);
//		return vo;
//	}
	
	public BlogVo getBlogValue(BlogVo blogVo) {
		BlogVo vo = blogRepository.getBlogValue(blogVo);
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

	public List<CategoryVo> getCateValueById(String id) {
		List<CategoryVo> postCounts = blogRepository.getCateValueById(id);
		 return postCounts;
	}

	public Map<String, Object> getAll(String id, Long categoryNo, Long postNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(categoryNo != 0L && postNo != 0L) {
			HashMap<String, Object> setMap = new HashMap<String, Object>();
			setMap.put("id", id);
			setMap.put("categoryNo", categoryNo);
			setMap.put("postNo", postNo);
			System.out.println("setMap " +setMap);
			PostVo mainPost =  blogRepository.getCategoryPost(setMap);
			List<PostVo> postList = blogRepository.getCategoryPostList(setMap);

			map.put("mainPost", mainPost);
			map.put("postList", postList);
		} else if (categoryNo != 0L && postNo == 0L) {
			HashMap<String, Object> setMap = new HashMap<String, Object>();
			setMap.put("id", id);
			setMap.put("categoryNo", categoryNo);
			PostVo mainPost = blogRepository.getCategoryMainPost(setMap);
			List<PostVo> postList = blogRepository.getCategoryPostList(setMap);
			
			map.put("mainPost", mainPost);
			map.put("postList", postList);
		} else {
			UserVo vo = new UserVo();
			vo.setId(id);
			PostVo mainPost = blogRepository.getBlogMainPost(vo);
			List<PostVo> postList = blogRepository.getBlogMainPostList(vo);
			
			map.put("mainPost", mainPost);
			map.put("postList", postList);
		}
		return map;
	}

	
 
}
