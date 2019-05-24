package com.oracle.web.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.web.bean.PageBean;
import com.oracle.web.bean.User;
import com.oracle.web.service.UserService;

@Controller
@Scope(value="prototype")
public class UserHandler {
	
	@Autowired
	private UserService userService;
	
	private File touxiang;
	
	private String touxiangContentType;
	
	private String touxiangFileName;
	
	
	
	public File getTouxiang() {
		return touxiang;
	}


	public void setTouxiang(File touxiang) {
		this.touxiang = touxiang;
	}


	public String getTouxiangContentType() {
		return touxiangContentType;
	}


	public void setTouxiangContentType(String touxiangContentType) {
		this.touxiangContentType = touxiangContentType;
	}


	public String getTouxiangFileName() {
		return touxiangFileName;
	}


	public void setTouxiangFileName(String touxiangFileName) {
		this.touxiangFileName = touxiangFileName;
	}


		//分页
		@RequestMapping(value="/showUserByPage",method=RequestMethod.GET)
		public  String showByPage(HttpServletRequest request,Integer pageNow){
			
			
			if(pageNow==null||pageNow<0){
				
				pageNow=1;
			}
			
			PageBean<User> pb=this.userService.showByPage(pageNow);
			
			request.setAttribute("pb", pb);
			
			return "showUser";
			
			
		}
		
		
		//添加
		@RequestMapping(value="/addUser",method=RequestMethod.POST)
		public String addUser(String uname,String username,String password,String phone,String regtime, MultipartFile touxiang) throws Exception{
			
			File file = new File("upload");
			
			touxiang.transferTo(file);
			
			String path=touxiang.getOriginalFilename();
			
			String valPath="upload"+path;
			
			User user=new User(null, uname, username, password, phone, regtime, valPath);
			
			int i=this.userService.addUser(user);
			
			
			if(i>0){
				
				return "redirect:/showUserByPage";
				
			}else{
				
				return "addUser";
			}
			
			
			
		}
		
		
		//删除
		@RequestMapping(value = "/deleteUser/{ids}/{pageNow}", method = RequestMethod.DELETE)
		public String deleteUser(@PathVariable(value = "ids") String ids, @PathVariable(value = "pageNow") Integer pageNow){
			
			this.userService.deleteUser(ids);
			
			return "redirect:/showUserByPage"+pageNow;
			
		}
		
		
		
		
		
		
		
		
		

}
