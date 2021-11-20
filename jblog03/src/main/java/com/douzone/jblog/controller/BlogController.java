package com.douzone.jblog.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.AdminService;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets|images).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping({"","/{categoryNo}","/{categoryNo}/{postNo}"})
		public String main (
			@PathVariable("id") String blogId,
			@PathVariable(value="categoryNo", required=false) Long categoryNo,
			@PathVariable(value="postNo", required=false) Long postNo,
			Model model){
		if(categoryNo != null && postNo != null) {
			PostVo post = blogService.getPost(blogId, categoryNo, postNo);
			BlogVo blogVo = blogService.getImageTitle(blogId);
			List<PostVo> postList = adminService.getLatestPost(blogId, categoryNo);
			List<CategoryVo> catList = blogService.getCategories(blogId);
			model.addAttribute("blogVo", blogVo);
			model.addAttribute("postList", postList);
			model.addAttribute("catList", catList);
			model.addAttribute("post", post);
			return "blog/blog-main";
			
		} else if(categoryNo != null) {
			BlogVo blogVo = blogService.getImageTitle(blogId);
			List<PostVo> postList = adminService.getLatestPost(blogId, categoryNo);
			List<CategoryVo> catList = blogService.getCategories(blogId);
			model.addAttribute("blogVo", blogVo);
			model.addAttribute("postList", postList);
			model.addAttribute("catList", catList);
			
			return "blog/blog-main";
			
		} else if(blogId != null) {
			BlogVo blogVo = blogService.getImageTitle(blogId);
			List<PostVo> postList = blogService.getLatestPost(blogId);
			List<CategoryVo> catList = blogService.getCategories(blogId);
			model.addAttribute("blogVo", blogVo);
			model.addAttribute("postList", postList);
			model.addAttribute("catList", catList);
			
			return "blog/blog-main";
		} else {
			return "main/index";
		}
	}
	
	@Auth
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String getToAdmin (
			@PathVariable("id") String blogId, 
			Model model) {

			BlogVo vo = blogService.getImageTitle(blogId);
			servletContext.setAttribute("imgTitleVo", vo);
			return "blog/blog-admin-basic";
	}
	
	@Auth
	@RequestMapping(value="/admin", method=RequestMethod.POST)
	public String adminPostImage (
			@AuthUser UserVo authUser,
			@PathVariable("id") String blogId, 
			@RequestParam(value="title", required=true, defaultValue="") String title,
			@RequestParam("logo-file") MultipartFile file) {

			if(file.isEmpty()) {
				blogService.saveImage(title, blogId);
				return "redirect:/"+ blogId +"/admin";
			} else {
			blogService.saveImage(file, title, blogId);
			return "redirect:/"+ blogId +"/admin";
			}
	}
	
	@Auth
	@RequestMapping(value="/admin/category")
	public String getToAdminCategory () {
			return "blog/blog-admin-category";
	}
	/*
	@Auth
	@RequestMapping(value="/admin/category", method=RequestMethod.POST)
	public String adminPostCategory (
			@PathVariable("id") String blogId,
			@RequestParam(value = "name", required = true, defaultValue = "미분류") String name,
			@RequestParam(value = "desc", required = true, defaultValue = "내용 없음") String desc) {

			adminService.categoryAdd(blogId, name, desc);
			return "redirect:/"+ blogId +"/admin/category";
	}
	
	@Auth
	@RequestMapping(value="/admin/delete", method=RequestMethod.POST)
	public String adminDelCategory (
			@PathVariable("id") String blogId,
			@RequestParam(value = "no", required = true, defaultValue = "") Long no) {

			adminService.categoryDel(no);
			return "redirect:/"+ blogId +"/admin/category";
	}
	

	@ResponseBody
	@GetMapping("/check/{no}")
	public JsonResult checkDel(
			@PathVariable("id") String blogId,
			@PathVariable("no") Long no) {
		Map<String, Object> map = new HashMap<>();
		boolean lastCategory = false;
		boolean existPost = false;

		List<CategoryVo> catList = blogService.getCategories(blogId);
		
		if(catList.size() == 1) {
			lastCategory = true;
		}
		
		List<PostVo> postList = blogService.getLatestPost(blogId, no);
	
		if(postList.size() != 0) {
			existPost = true;
		}
		
		map.put("lastCategory", lastCategory);
		map.put("existPost", existPost);

		return JsonResult.success(map);
	}
	*/
	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.GET)
	public String getToAdminWrite (
			@AuthUser UserVo authUser,
			@PathVariable("id") String blogId,
			Model model) {

			List<BlogVo> list = adminService.getCategories(blogId);
			model.addAttribute("list", list);
			return "blog/blog-admin-write";
	}
	
	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String adminPostWrite (
			@AuthUser UserVo authUser,
			@PathVariable("id") String blogId,
			@RequestParam(value = "title", required = true, defaultValue = "") String title,
			@RequestParam(value = "category", required = true, defaultValue = "") String categoryName,
			@RequestParam(value = "contents", required = true, defaultValue = "") String contents,
			Model model) {
		
			adminService.writePost(blogId, categoryName, title, contents);
			List<BlogVo> list = adminService.getCategories(blogId);
			model.addAttribute("list", list);
			return "blog/blog-admin-write";
	}
	
}
