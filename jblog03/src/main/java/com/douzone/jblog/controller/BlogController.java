package com.douzone.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@RequestMapping(value="/blog-main", method=RequestMethod.GET)
	public String blogMain() {
		return "blog/blog-main";
	}
	
	@RequestMapping(value="/blog-admin-basic", method=RequestMethod.GET)
	public String blogAdminBasic() {
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/blog-admin-category", method=RequestMethod.GET)
	public String blogAdminCategory() {
		return "blog/blog-admin-category";
	}
	
	@RequestMapping(value="/blog-admin-write", method=RequestMethod.GET)
	public String blogAdminWrite() {
		return "blog/blog-admin-write";
	}
}
