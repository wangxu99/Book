package com.oracle.web.controller;

 

import java.io.IOException;
import java.util.List;

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

import com.oracle.web.bean.Book;
import com.oracle.web.bean.Fenlei;
import com.oracle.web.bean.PageBean;
import com.oracle.web.service.FenleiService;
 
 
@Controller
@Scope(value = "prototype")
public class FenleiHandler {
	 
	@Autowired
	private FenleiService fenleiService;
	
	@RequestMapping(value="/fenlei1",method=RequestMethod.GET)
	public String fenleis(HttpServletRequest request) {


	
		
			List<Fenlei> list = fenleiService.list();


			System.out.println(list);

			
			request.setAttribute("fList", list);


			return "showFenlei";
		}
		
	 
//	@RequestMapping(value="/addUI",method=RequestMethod.GET)
//	public String addUI(HttpServletRequest request){
//		
//		System.out.println("add");
//		
//		return "addFenlei";
//		
//	}
//	
	@RequestMapping(value="/fenlei",method=RequestMethod.POST)
	public String add(Fenlei fenlei){
		
		fenleiService.save(fenlei);
		
		return "redirect:/fenleis/1";
		
	}
	
	@RequestMapping(value = "/fenlei_delete/{fid}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("fid") Integer id) {


		Fenlei f = new Fenlei();


		f.setFid(id);


		fenleiService.delete(f);


		return "redirect:/fenleis/1";


	}
	
	
	@RequestMapping(value = "/fenlei/{fid}", method = RequestMethod.GET)
	public String updateUI(@PathVariable("fid") Integer fid, HttpSession session) {

          
		Fenlei fenlei = fenleiService.selectByPrimaryKey(fid);

		List<Fenlei> flist = this.fenleiService.selectFenleiAll();
		session.setAttribute("flist", flist);
	 
		session.setAttribute("f", fenlei);
 
		return "updatefl";


	}
	
	@RequestMapping(value = "/fenleiupdate", method = RequestMethod.PUT)
	public String update(Fenlei fenlei) {


		fenleiService.update(fenlei);


		return "redirect:/fenleis/1";
		
	}
	
	
	@RequestMapping(value = "/fenleis/{pageNow}", method = RequestMethod.GET)
	public String list(@PathVariable(value = "pageNow") Integer pageNow, HttpServletRequest request) {




		if (pageNow == null || pageNow < 1) {


			pageNow = 1;


		}


		PageBean<Fenlei> pb = this.fenleiService.selectAllByPageHelper(pageNow);


		request.setAttribute("pb", pb);
		
		System.out.println(pb);


		return "showFenlei";
	}
	
	// 验证修改分类
		@RequestMapping(value = "/yzfenleiupdate", method = RequestMethod.GET)
		public void yzfenleiupdate( @RequestParam(value = "fname") String fname, @RequestParam(value = "fid") Integer fid ,HttpServletResponse response) throws IOException {
			// 调用service进行查询
			// System.out.println(userName);
			response.setContentType("text/html;charset=UTF-8");
			Fenlei f = this.fenleiService.yanzhengAddFenlei(fname);

			// System.out.println(b.toString());
			// 获取response对象，向页面输出信息

			// 判断是否为空
			if (f == null) {
				// bid 一样没改变是一本书 ，可修改
				// b没空 该分类下没有该图书可添加
				
				int a= this.fenleiService.yanzhengAddFenlei2(fid);
				if(a==0){
				response.getWriter().write("{\"valid\":\"true\"}");
				}else{
					response.getWriter().write("{\"valid\":\"false\"}");
				}
			  
			} else {
				// 分类下图书已存在，不能添加

				response.getWriter().write("{\"valid\":\"false\"}");

			}
			// AJAX操作，不需要页面跳转

		}
	
	
	
} 
