package com.douzone.jblog.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.service.AdminService;
import com.douzone.jblog.vo.BlogVo;

public class SiteInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	AdminService adminService;
	
	@Autowired
	ServletContext servletContext;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
			BlogVo blogVo = (BlogVo) request.getServletContext().getAttribute("blogVo");
			
			if(blogVo == null) {
				//blogVo = adminService.getSite();
				servletContext.setAttribute("blogVo", blogVo);
			} 
			
		return true;
	}
	
	
}
