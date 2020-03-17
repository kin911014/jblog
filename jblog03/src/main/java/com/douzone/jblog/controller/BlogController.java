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
}
