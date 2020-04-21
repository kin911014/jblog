package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository; 
	
	public void join(UserVo userVo) {
		userRepository.join(userVo);
//		blogRepository.insert(userVo);
	}

	public UserVo findByEmailAndPassword(UserVo userVo) {
		
		return userRepository.findByEmailAndPassword(userVo);
	}

}
