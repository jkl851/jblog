package com.douzone.jblog.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.AdminService;
import com.douzone.jblog.vo.CategoryVo;


@RestController("blogApiController")
@RequestMapping("/api/{id:(?!assets|images).*}/admin/category")
public class BlogController {
	@Autowired
	private AdminService adminService;
	
	@Auth
	@ResponseBody
	@GetMapping("")
	public JsonResult list(@PathVariable("id") String blogId) throws Exception {
		
		System.out.println("API 컨트롤러 list 호출");
		List<CategoryVo> list = adminService.getCategoryPostCounts(blogId);
		return JsonResult.success(list);
	}
	
	@Auth
    @ResponseBody
    @PostMapping("")
    public JsonResult adminCategory(
    		@PathVariable("id") String blogId, 
    		@RequestBody CategoryVo categoryVo) {
		 
		categoryVo.setBlog_id(blogId);
		adminService.categoryAdd(categoryVo);
		CategoryVo vo = adminService.getCategoryAjax(blogId);
		System.out.println(vo);
		return JsonResult.success(vo);
	}
	
//	@Auth
//	@ResponseBody
//	@GetMapping("/check/{no}")
//	public JsonResult checkDel(
//			@PathVariable("id") String blogId,
//			@PathVariable("no") Long no) {
//		
//		Map<String, Object> map = new HashMap<>();
//		boolean lastCategory = false;
//		boolean existPost = false;
//
//		List<CategoryVo> catList = adminService.getCategoriesAjax(blogId);
//		
//		if(catList.size() == 1) {
//			lastCategory = true;
//		}
//		
//		List<PostVo> postList = adminService.getLatestPost(blogId, no);
//	
//		if(postList.size() != 0) {
//			existPost = true;
//		}
//		
//		map.put("lastCategory", lastCategory);
//		map.put("existPost", existPost);
//
//		return JsonResult.success(map);
//	}
	
	@Auth
	@ResponseBody
	@PostMapping("/del/{no}")
	public JsonResult delete(@PathVariable("no") Long no) {
		System.out.println(no);
		boolean result = adminService.categoryDel(no);
		return JsonResult.success(result ? no : -1);
	}
}