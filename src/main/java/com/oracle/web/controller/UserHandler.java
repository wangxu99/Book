package com.oracle.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.web.bean.PageBean;
import com.oracle.web.bean.User;
import com.oracle.web.service.UserService;

@Controller
@Scope(value="prototype")
public class UserHandler {
	
	@Autowired
	private UserService userService;
	
	//分页
		@RequestMapping(value="/showByPage",method=RequestMethod.GET)
		public  String showByPage(HttpServletRequest request,Integer pageNow){
			
			
			if(pageNow==null||pageNow<0){
				
				pageNow=1;
			}
			
			PageBean<User> pb=this.userService.showByPage(pageNow);
			
			request.setAttribute("pb", pb);
			
			return "showAll";
			
			
		}
		

}
