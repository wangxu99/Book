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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.web.bean.Admin;
import com.oracle.web.bean.User;
import com.oracle.web.service.AdminService;

@Controller
@Scope(value = "prototype")
public class AdminHandler {

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/addadmin", method = RequestMethod.POST)
	public String register(String aname, String phone, String ausername, String password,
		 MultipartFile touxiang, HttpServletRequest request)
			throws IllegalStateException, IOException {

		String str = request.getSession().getServletContext().getRealPath("/upload");
		
		int hashCode = touxiang.getOriginalFilename().hashCode();

		String hex = Integer.toHexString(hashCode);

		char c1 = hex.charAt(0);

		char c2 = hex.charAt(1);

		// realPath=realPath+"/"+c1+"/"+c2;

		String redlName = UUID.randomUUID().toString() + "_" + touxiang.getOriginalFilename();

		String savepath = "/" + c1 + "/" + c2 + "/" + redlName;

		File saveFile = new File(str + savepath);

		saveFile.mkdirs();

		// 真正上传
		touxiang.transferTo(saveFile);
 
		String touxiang2 = "upload" + savepath;
		 
		System.out.println(touxiang2);

		Admin admin = new Admin(null,aname, ausername, password, phone , touxiang2);
		//Integer aid, String aname, String ausername, String password, String phone, String touxiang

		adminService.save(admin);

		return "login";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam("ausername") String ausername, @RequestParam(value = "password") String password,
			HttpSession session) {

		Admin a = adminService.login(ausername);

		if (a == null) {

			return "login";

		} else if (!password.equals(a.getPassword())) {

			return "login";

		} else {

			session.setAttribute("ausername", ausername);
			session.setAttribute("aid", a.getAid());

			return "index";

		}

	}
	
	 // 管理员登录校验
	@RequestMapping(value = "/adminloginYZ", method = RequestMethod.GET)
	public void userloginYZ(@RequestParam(value = "ausername") String ausername,
			@RequestParam(value = "password") String password, HttpServletResponse response) throws IOException {
        System.out.println(ausername+"---"+password);
		Admin admin2 = this.adminService.login(ausername);
		//System.out.println(admin2.toString());

		if (admin2 == null) {
			response.getWriter().write("{\"valid\":\"false\"}");

		}else if (!password.equals(admin2.getPassword())) {
			
			response.getWriter().write("{\"valid\":\"false\"}");
		} else {

			response.getWriter().write("{\"valid\":\"true\"}");
		}

	}
	
	
   //管理员注册校验
	@RequestMapping(value = "/AdminServlet", method = RequestMethod.GET)
	public void queryByUsername(@RequestParam("ausername") String ausername, HttpServletResponse response)
			throws IOException {

		System.out.println(ausername);

		Admin a = adminService.login(ausername);

		if (a != null) {

			response.getWriter().write("{\"valid\":\"false\"}");

		} else {

			response.getWriter().write("{\"valid\":\"true\"}");

		}

	}
     
	//查看管理员信息
	@RequestMapping(value = "/showAdmin", method = RequestMethod.GET)
	public String showAdmin(HttpServletResponse response, HttpServletRequest request, HttpSession session) {

		String uname = (String) session.getAttribute("ausername");

		Admin a = adminService.showAdmin(uname);

		request.setAttribute("a", a);

		return "showAdmin";
	}
	
	//验证密码
	@RequestMapping(value = "/adminpasswordYZ", method = RequestMethod.GET)
	public void adminloginYZ(@RequestParam(value = "ausername") String ausername,
			@RequestParam(value = "password") String password, HttpServletResponse response) throws IOException {
        System.out.println(ausername+"---"+password);
		Admin admin2 = this.adminService.login(ausername);
		//System.out.println(admin2.toString());

		if (admin2 == null) {
			response.getWriter().write("{\"valid\":\"false\"}");

		}else if (!password.equals(admin2.getPassword())) {
			
			response.getWriter().write("{\"valid\":\"false\"}");
		} else {

			response.getWriter().write("{\"valid\":\"true\"}");
		}

	}
	
    
	
     //修改密码
	@RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
	public String updatePassword(@RequestParam(value = "newpassword") String newpassword,HttpServletResponse response, HttpServletRequest request) {

		HttpSession session = request.getSession();

		Integer aid = (Integer) session.getAttribute("aid");

	//	String newpassword = request.getParameter("newpassword");

		int i = adminService.updatePassword(aid, newpassword);

		return "tc";

	}
}
