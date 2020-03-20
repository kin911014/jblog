package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets|images).*}")
public class BlogController {
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private BlogService blogService;
	
	private void blogVo(Model model, String id) {
		BlogVo vo = blogService.findFileName(id);
		System.out.println("vo의 값 "+vo);
		model.addAttribute("blogVo", vo);
		
	}
	
	@RequestMapping({ "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
	public String blogMain( 
//			BlogVo blogVo, Model model, HttpSession session, CategoryVo categoryVo
			Model model,
			@PathVariable String id, 
			@PathVariable Optional<Long> pathNo1,
			@PathVariable Optional<Long> pathNo2, 
			ModelMap modelMap
			) {
		
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if(id == null) {
			return "blog/blog-main";
		} 
		
		blogVo(model,id);
		List<CategoryVo> getValues = blogService.categoryPostCount(id);
		System.out.println("getValues의 값 "+getValues);
		
		
		return "blog/blog-main";
//		if(id != null) {
//		BlogVo blogVo = new BlogVo();
//		blogVo.setId(id);
//		BlogVo vo = blogService.findFileName(blogVo);
//		String url = vo.getLogo();
//		System.out.println(url);
//		model.addAttribute("url", url);
//			
//		CategoryVo categoryVo = new CategoryVo();
//		categoryVo.setId(id);
//		System.out.println("2 "+categoryVo);
//		model.addAttribute("getValues", getValues);
//			
//			
//			return "blog/blog-main";
//		}else {
//			
//			return "blog/blog-main";
//		}
	}
	

	@RequestMapping(value="/blog-admin-basic", method=RequestMethod.GET)
	public String blogAdminBasic(BlogVo blogVo, Model model, HttpSession session) {
	//////////////////////접근제한//////////////////////////////
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
		return "redirect:/";
		}
	//////////////////////접근제한//////////////////////////////
		
//		String url = vo.getLogo();
//		String title = vo.getTitle();
//		model.addAttribute("url", url);
//		model.addAttribute("title", title);
		
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

		return "redirect:/blog/blog-main";
	}
	
	@RequestMapping(value="/blog-admin-category", method=RequestMethod.GET)
	public String blogAdminCategory(HttpSession session,
			Model model,
			@PathVariable String id 
	/*,CategoryVo categoryVo*/){
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
//		categoryVo.setId(authUser.getId());
		List<CategoryVo> getValues = blogService.categoryPostCount(id);
		model.addAttribute("getValues", getValues);
		
		return "blog/blog-admin-category";
	}
	
	@RequestMapping(value="/blog-admin-category/{no}", method=RequestMethod.GET)
	public String blogAdminCategory(@PathVariable("no") Long no,HttpSession session, CategoryVo categoryVo, Model model) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		categoryVo.setNo(no);
		blogService.categoryDelete(categoryVo);
		
		return "redirect:/blog/blog-admin-category";
	}
	
	@RequestMapping(value="/blog-admin-category", method=RequestMethod.POST)
	public String blogAdminCategory(HttpSession session, CategoryVo categoryVo, Model model) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		
		categoryVo.setId(authUser.getId());
		blogService.categoryInsert(categoryVo);
		
		
		List<CategoryVo> getValues = blogService.categoryGet(categoryVo);
		
		model.addAttribute("getValues", getValues);
		
		return "redirect:/blog/blog-admin-category";
	}
	
	@RequestMapping(value="/blog-admin-write", method=RequestMethod.GET)
	public String blogAdminWrite(Model model) {
		List<CategoryVo> categoryNames = blogService.getCategoryName();
		model.addAttribute("categoryNames", categoryNames);
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value="/blog-admin-write", method=RequestMethod.POST)
	public String blogAdminWrite(
			@PathVariable String id,
			PostVo postVo) {
		blogService.writeInsert(postVo);
		
		return "redirect:/" +id+ "/blog-admin-write";
	}
}
