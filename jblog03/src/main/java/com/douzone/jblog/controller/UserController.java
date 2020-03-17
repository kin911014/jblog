package com.douzone.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService; 
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpSession session, UserVo userVo) {
		UserVo authUser = userService.findByEmailAndPassword(userVo);
		if(authUser == null) {
			return "user/login";
		}
		session.setAttribute("authUser", authUser);
		return "/blog/blog-main";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVo userVo) {
		userService.join(userVo);
		return "user/join";
	}
	
}
