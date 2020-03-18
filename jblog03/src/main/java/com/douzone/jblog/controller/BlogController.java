package com.douzone.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private BlogService blogService;
	@RequestMapping(value="/blog-main", method=RequestMethod.GET)
	public String blogMain(BlogVo blogVo, Model model, HttpSession session) {
		//////////////////////접근제한//////////////////////////////
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		//if(authUser == null) {
		//return "redirect:/";
		//}
		//////////////////////접근제한//////////////////////////////
		String id = authUser.getId();
		System.out.println("id1 "+id);
		blogVo.setId(id);
		BlogVo vo = blogService.findFileName(blogVo);
		String url = vo.getLogo();
		model.addAttribute("url", url);

		
		return "blog/blog-main";
	}
	
	@RequestMapping(value="/blog-admin-basic", method=RequestMethod.GET)
	public String blogAdminBasic(BlogVo blogVo, Model model, HttpSession session) {
	//////////////////////접근제한//////////////////////////////
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
		return "redirect:/";
		}
	//////////////////////접근제한//////////////////////////////
		
		String id = authUser.getId();
		blogVo.setId(id);
		BlogVo vo = blogService.findFileName(blogVo);
		String url = vo.getLogo();
		String title = vo.getTitle();
		model.addAttribute("url", url);
		model.addAttribute("title", title);
		
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/blog-admin-basic", method=RequestMethod.POST)
	public String blogAdminBasic(
			HttpSession session
			,@RequestParam(value="title", required=true, defaultValue="") String title, 
			@RequestParam(value="logo") MultipartFile multipartFile,
			Model model
			) {
		
		//////////////////////접근제한//////////////////////////////
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		//////////////////////접근제한//////////////////////////////
		
		// 사진 url
		String url = fileUploadService.restore(multipartFile);
		model.addAttribute("url", url);
		
		BlogVo blogVo = new BlogVo();
		String id = authUser.getId();
		blogVo.setId(id);
		blogVo.setTitle(title);
		blogVo.setLogo(url);
		
		blogService.upload(blogVo);

		return "/blog/blog-main";
	}
	
	@RequestMapping(value="/blog-admin-category", method=RequestMethod.GET)
	public String blogAdminCategory() {
		return "blog/blog-admin-category";

		
	}
	
	@RequestMapping(value="/blog-admin-write", method=RequestMethod.GET)
	public String blogAdminWrite() {
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value="/blog-admin-write", method=RequestMethod.POST)
	public String blogAdminWrite(PostVo postVo) {
//		int blogWrite = blogService.write(postVo);
		return "blog/blog-admin-write";
	}
}
