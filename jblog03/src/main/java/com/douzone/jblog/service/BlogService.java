package com.douzone.jblog.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.exception.GalleryServiceException;
import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class BlogService {
	private static String SAVE_PATH = "/upload-mysite/jblog-img";
	private static String URL_BASE = "/images";	
	
	@Autowired
	private BlogRepository blogRepository;
	
	public BlogVo getImageTitle(String blogId) {
		return blogRepository.getImageTitle(blogId);
	}
	
	public List<PostVo> getPostsAndCategories(String blogId) {
		return blogRepository.getPostsAndCategories(blogId);
	}
	
	public List<PostVo> getLatestPost(String blogId) {
		return blogRepository.getLatestPost(blogId);
	}
	
	public PostVo getPost(String blogId, Long categoryNo, Long postNo) {
		PostVo vo = new PostVo();
		vo.setBlog_id(blogId);
		vo.setCatNo(categoryNo);
		vo.setPostNo(postNo);
		return blogRepository.getPost(vo);
	}
	
	public List<CategoryVo> getCategories(String blogId) {
		return blogRepository.getCategories(blogId);
	}
	
	public void saveImage(MultipartFile file, String title, String blogId) throws GalleryServiceException {
		try {
			File uploadDirectory = new File(SAVE_PATH);
			if(!uploadDirectory.exists()) {
				uploadDirectory.mkdir();
			}
			
			if(file.isEmpty()) {
				throw new GalleryServiceException("file upload error: image empty");
			}
			
			String originFilename = file.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf('.')+1);
			String saveFilename = generateSaveFilename(extName);
			
			byte[] data = file.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
			os.write(data);
			os.close();
			
			BlogVo vo = new BlogVo();
			vo.setLogo(URL_BASE + "/" + saveFilename);
			vo.setTitle(title);
			vo.setId(blogId);
			
			blogRepository.insert(vo);
			
		} catch(IOException ex) {
			throw new GalleryServiceException("file upload error:" + ex);
		}
	}
	
	public void saveImage(String title, String blogId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", title);
		map.put("blogId", blogId);
		blogRepository.insert(map);
	}
	
	private String generateSaveFilename(String extName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}
	
	
}
