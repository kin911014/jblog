package com.douzone.jblog.controller.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.mysite.dto.JsonResult;

@RestController("BlogApiController")
@RequestMapping("/{id:(?!assets).*}/api/blog")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@GetMapping("/list")
	public JsonResult list(@PathVariable("id")Optional<String> id) {
		List<CategoryVo> list = blogService.findAllById(id.get());
		return JsonResult.success(list);
	}
	
	@PostMapping("/add")
	public JsonResult add(@PathVariable("id") String id,@RequestBody CategoryVo vo) {
		vo.setId(id);
		blogService.categoryInsert(vo);
		return JsonResult.success(vo);
	}
	
}
