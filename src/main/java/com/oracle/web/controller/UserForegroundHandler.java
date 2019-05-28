package com.oracle.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
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

import com.oracle.web.bean.Admin;
import com.oracle.web.bean.Book;
import com.oracle.web.bean.BookAndFenlei;
import com.oracle.web.bean.Fenlei;
import com.oracle.web.bean.PageBean;
import com.oracle.web.bean.User;
import com.oracle.web.service.BookService;
import com.oracle.web.service.FenleiService;
import com.oracle.web.service.UserService;

@Controller
@Scope(value = "prototype")
public class UserForegroundHandler {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;

	@Autowired
	private FenleiService fenleiService;
 
	
	//用户登录
	@RequestMapping(value="/userloginYanZheng",method=RequestMethod.GET)
	public String loginYanZheng(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,HttpSession session){ 
		 
		 User user2=this.userService.UserLoginYanZheng(username);
		
	 	 
		if (user2 == null) {
			//request.getWriter().write("{\"valid\":\"false\"}");

			return "userlogin";
		}

		if (!user2.getPassword().equals(password)) {
			//resp.getWriter().write("{\"valid\":\"false\"}");
			return "userlogin";
		} else {

			//resp.getWriter().write("{\"valid\":\"true\"}");
			session.setAttribute("username", username);
			return "userindex";
		}
 
		} 
	
	
	//用户登录校验
		@RequestMapping(value="/userloginYZ",method=RequestMethod.GET)
	public void userloginYZ(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, HttpServletResponse response) throws IOException{ 
			 
			 User user2=this.userService.UserLoginYanZheng(username);
			
		 	 
			if (user2 == null) {
				 response.getWriter().write("{\"valid\":\"false\"}");

				
			}

			if (!user2.getPassword().equals(password)) {
				//resp.getWriter().write("{\"valid\":\"false\"}");
				response.getWriter().write("{\"valid\":\"false\"}");
			} else {

				 
				response.getWriter().write("{\"valid\":\"true\"}");
			}
	 
			} 
	
	// 全查分页
		@RequestMapping(value = "/userForegroundBook/{pageNow}", method = RequestMethod.GET)
		public String userForegroundBook(@PathVariable(value = "pageNow") Integer pageNow, HttpServletRequest request) {
			// System.out.println("访问页数" + pageNow);
			/*
			 * if (pageNow == null || pageNow < 1) { pageNow = 1; }
			 */
			PageBean<BookAndFenlei> pb = this.bookService.showBookPesgeAll(pageNow);
			 
			List<Fenlei> flist = this.fenleiService.selectFenleiAll();
			request.setAttribute("flist", flist);
			request.setAttribute("pb", pb);
			request.setAttribute("showPesge", "showBook");// 控制页面跳转

			return "userForeground";

		}
		 
	// 图书高级搜索
		@RequestMapping(value = "/userForegroundGaoJiSs", method = RequestMethod.GET)
		public String userForegroundGaoJiSs(Book book, @RequestParam(value = "pageNow") Integer pageNow, HttpServletRequest request) {
			if (pageNow == null || pageNow < 1) {
				pageNow = 1;
			}
			PageBean<BookAndFenlei> pb = this.bookService.showBookPesgeGaoJi(pageNow, book);

			List<BookAndFenlei> list = pb.getBeanList();
			String url = this.getUrl2(request);
			pb.setUrl(url);
			List<Fenlei> flist = this.fenleiService.selectFenleiAll();
			request.setAttribute("flist", flist);
			request.setAttribute("pb", pb);
			request.setAttribute("showPesge", "gao");// 控制页面跳转

			return "userForeground";
		}
		private String getUrl2(HttpServletRequest req) {
			// TODO Auto-generated method stub
			String url = this.getUrl(req);
			int index = url.lastIndexOf("&pageNow=");
			if (index == -1) {
				return url;
			}
			url = url.substring(0, index);
			//System.out.println(url);
			return url;
		}

		private String getUrl(HttpServletRequest req) {
			// TODO Auto-generated method stub
			String path = req.getContextPath();
			//System.out.println(path);
			String servlet = req.getServletPath();
			//System.out.println(servlet);
			String param = req.getQueryString();
			//System.out.println(param);
			//System.out.println(path + servlet + "?" + param);
			return path + servlet + "?" + param;
		}
}
