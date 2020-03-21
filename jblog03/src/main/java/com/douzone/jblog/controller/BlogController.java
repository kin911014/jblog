package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		
		BlogVo blogVo = new BlogVo();
		blogVo.setId(id);
		BlogVo vo = blogService.getBlogValue(blogVo);
		System.out.println("id "+id);
		System.out.println("vo의 값 "+vo);
		model.addAttribute("blogVo", vo);
		
	}
	
	@RequestMapping({ "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
	public String blogMain( 
			Model model,
			@ModelAttribute("id") @PathVariable String id, 
			@PathVariable Optional<Long> pathNo1,
			@PathVariable Optional<Long> pathNo2, 
			ModelMap modelMap
			) {
		
		blogVo(model,id);
		
		// category cnt number
		List<CategoryVo> getValues = blogService.getCateValueByCnt(id);
		System.out.println("getValues의 값 "+getValues);
		model.addAttribute("getValues", getValues);
		
		
		if(id == null) {
			return "blog/blog-main";
		}
		
		
		Long categoryNo = 0L;
		Long postNo = 0L;
		

		if (pathNo2.isPresent()) {
			postNo = pathNo2.get();
			categoryNo = pathNo1.get();
		} else if (pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		
		
		
		
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
	public String blogAdminBasic(@ModelAttribute("id") @PathVariable String id, Model model, HttpSession session) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null || !id.equals(authUser.getId())) {
			return "redirect:/";
		}
		//////////////////////접근제한//////////////////////////////
		
		
		blogVo(model,id);
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/blog-admin-basic", method=RequestMethod.POST)
	public String blogAdminBasic(
			HttpSession session
			,@RequestParam(value="title", required=true, defaultValue="") String title, 
			@RequestParam(value="logo") MultipartFile multipartFile,
			@PathVariable String id,
			Model model
			) {
		
		
		// 사진 url
		String url = fileUploadService.restore(multipartFile);
		BlogVo blogVo = new BlogVo();
		
		if(url.equals("")) {
			
			blogVo.setId(id);
			BlogVo vo = blogService.getBlogValue(blogVo);
			blogService.upload(vo);
			
		}else {
			blogVo.setId(id);
			blogVo.setTitle(title);
			blogVo.setLogo(url);
			blogService.upload(blogVo);
			BlogVo vo =blogService.getBlogValue(blogVo);
			model.addAttribute("blogVo",vo);
		}
		

		return "redirect:/" + id + "/blog-admin-basic";
	}
	
	@RequestMapping(value="/blog-admin-category{no}", method=RequestMethod.GET)
	public String blogAdminCategory(HttpSession session,
			Model model,
			@ModelAttribute("id") @PathVariable String id,
			@PathVariable Long no,
			CategoryVo categoryVo){
		
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null || !id.equals(authUser.getId())) {
			return "redirect:/";
		}
		//////////////////////접근제한//////////////////////////////
		
		// get category's value 
		List<CategoryVo> getValues = blogService.getCateValueByCnt(id);
		model.addAttribute("getValues", getValues);

		if(no != null) {
			categoryVo.setNo(no);
			blogService.categoryDelete(categoryVo);
			return "redirect:/" + id+ "/blog-admin-category";
		}
		
		
		return "blog/blog-admin-category";
	}
	
//	@RequestMapping(value="/blog-admin-category/{no}", method=RequestMethod.GET)
//	public String blogAdminCategory(@PathVariable("no") Long no,HttpSession session, CategoryVo categoryVo, Model model) {
//		
//		
//		UserVo authUser = (UserVo)session.getAttribute("authUser");
//		if(authUser == null) {
//			return "redirect:/";
//		}
//		categoryVo.setNo(no);
//		blogService.categoryDelete(categoryVo);
//		
//		return "redirect:/blog/blog-admin-category";
//	}
	
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
	public String blogAdminWrite(Model model,
			@ModelAttribute("id") @PathVariable String id) {
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
