package com.oracle.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.web.bean.PageBean;
import com.oracle.web.bean.User;
import com.oracle.web.service.UserService;

@Controller
@Scope(value = "prototype")
public class UserHandler {

	@Autowired
	private UserService userService;
	
	
	// 验证用户
	@RequestMapping(value="/validateUser",method=RequestMethod.GET)
	public String validateUser(String username,HttpServletResponse response) throws IOException{
		
		System.out.println(username);
		
		User a=this.userService.validateUser(username);
	
		
		if(a!=null){
			
			response.getWriter().write("{\"valid\":\"false\"}");//存在
		}else{
			
			response.getWriter().write("{\"valid\":\"true\"}");//不存在
		}
		
		
		return null;
	}

	// 分页查
	@RequestMapping(value = "/showUserByPage", method = RequestMethod.GET)
	public String showByPage(HttpServletRequest request, Integer pageNow) {

		if (pageNow == null || pageNow < 0) {

			pageNow = 1;
		}

		PageBean<User> pb = this.userService.showByPage(pageNow);

		request.setAttribute("pb", pb);

		return "showUser";

	}

	// 添加
	@RequestMapping(value = "/User", method = RequestMethod.POST)
	public String addUser(String uname, String username, String password, String phone, String regtime,
			MultipartFile touxiang, HttpSession session) throws Exception {

		String realPath = session.getServletContext().getRealPath("/upload");

		// System.out.println(file.getOriginalFilename());
		// String path ="/upload/"+file.getOriginalFilename();

		int hashCode = touxiang.getOriginalFilename().hashCode();

		String hex = Integer.toHexString(hashCode);

		char c1 = hex.charAt(0);

		char c2 = hex.charAt(1);

		// realPath=realPath+"/"+c1+"/"+c2;

		String redlName = UUID.randomUUID().toString() + "_" + touxiang.getOriginalFilename();

		String savepath = "/" + c1 + "/" + c2 + "/" + redlName;

		File saveFile = new File(realPath + savepath);

		saveFile.mkdirs();

		// 真正上传
		touxiang.transferTo(saveFile);

		String realsavepath = "upload" + savepath;
		
		System.out.println(realsavepath);

		User user = new User(null, uname, username, password, phone, regtime, realsavepath);

		int i = this.userService.addUser(user);

		session.setAttribute("url",realsavepath);

		if (i > 0) {

			return "redirect:/showUserByPage";

		} else {

			return "addUser";
		}

	}

	// 批量删除
	@RequestMapping(value = "/User/{ids}/{pageNow}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable(value = "ids") String ids,
			@PathVariable(value = "pageNow") Integer pageNow) {

		this.userService.deleteUser(ids);

		System.out.println("ids...");

		return "redirect:/showUserByPage" + pageNow;

	}

	// 修改用户  先查出来
	@RequestMapping(value = "/updateUl/{uid}", method = RequestMethod.GET)
	public String updateUl(@PathVariable(value = "uid") Integer id, HttpSession session) {

		User user = this.userService.selectOne(id);
		
		System.out.println(user);

		session.setAttribute("user", user);

		return "redirect:/changeUser.jsp";

	}
	
	//修改头像
	@RequestMapping(value = "/updateTouxiang", method=RequestMethod.POST)
	public String updateTouxiang(Integer uid,MultipartFile touxiang, HttpSession session) throws Exception{
		
		System.out.println("修改头像");
		
		String realPath = session.getServletContext().getRealPath("/upload");

		int hashCode = touxiang.getOriginalFilename().hashCode();

		String hex = Integer.toHexString(hashCode);

		char c1 = hex.charAt(0);

		char c2 = hex.charAt(1);

		String redlName = UUID.randomUUID().toString() + "_" + touxiang.getOriginalFilename();

		String savepath = "/" + c1 + "/" + c2 + "/" + redlName;

		File saveFile = new File(realPath + savepath);

		saveFile.mkdirs();

		// 真正上传
		touxiang.transferTo(saveFile);
		
		User user=new User();
		
		String realsavepath = "upload" + savepath;
		
		user.setUid(uid);
		
		user.setTouxiang(realsavepath);
		
		this.userService.updateTouxoiang(user);
		
		return "redirect:/updateUl/"+uid;
		
	}
	
	
	// 修改用户 
	@RequestMapping(value="User",method=RequestMethod.PUT)
	public String updateUser(User user){
		
		this.userService.updateUser(user);
		
		return "redirect:/showUserByPage";
		
	}
	
	

	// 导出
	
	
	
}
