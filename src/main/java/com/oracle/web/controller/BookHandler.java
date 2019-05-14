package com.oracle.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

 
import com.oracle.web.bean.Book;
import com.oracle.web.bean.BookAndFenlei;
import com.oracle.web.bean.Fenlei;
import com.oracle.web.bean.PageBean;
import com.oracle.web.service.BookService;
import com.oracle.web.service.FenleiService;

@Controller 
@Scope(value = "prototype")
public class BookHandler {

	@Autowired
	private BookService bookService;
  
	@Autowired
	private FenleiService fenleiService;
	
	//全查分页
	@RequestMapping(value = "/showBookHandler/{pageNow}", method = RequestMethod.GET)
	public String showPasgeMonster(@PathVariable(value = "pageNow") Integer pageNow, HttpServletRequest request) {// 分页查看
		//System.out.println("访问页数" + pageNow);
		/*
		 * if (pageNow == null || pageNow < 1) { pageNow = 1; }
		 */
		PageBean<BookAndFenlei> pb = this.bookService.showBookPesgeAll(pageNow);
          List<BookAndFenlei> list=pb.getBeanList();
        /*  for (BookAndFenlei bookAndFenlei : list) {
			System.out.println(bookAndFenlei.toString());
		}*/
        List<Fenlei> flist=this.fenleiService.selectFenleiAll();
          request.setAttribute("flist", flist);
		request.setAttribute("pb", pb);
		request.setAttribute("showPesge", "showBook");
		

		return "showBook";

	}
	 @RequestMapping(value="/yanzhengAddBook",method=RequestMethod.GET)
		public void yanzhengAddBook(@RequestParam(value="bname") String bname,@RequestParam(value="flid") String flid,HttpServletResponse response) throws IOException{
	       // 调用service进行查询
	    	//System.out.println(userName);
		 response.setContentType("text/html;charset=UTF-8");
	       int i= this.bookService.yanzhengAddBook(bname,flid);
	       
	      // System.out.println(existUser);
	        //获取response对象，向页面输出信息
	       
	       // 判断是否为空  
	        if(i==0){
	            //分类下图书不经存在，可以添加
	          //  有异常则向上抛出	
	        	response.getWriter().write("{\"valid\":\"true\"}");
	        } else{
	            //分类下图书已存在，不能添加
	        	 
	        	 response.getWriter().write("{\"valid\":\"false\"}");
	        	
	        }
	     //   AJAX操作，不需要页面跳转
	         
	   }
	
	 //添加图书界面,分类遍历
	@RequestMapping(value="/addBookUl",method=RequestMethod.GET)
	public String addBookUl(HttpServletRequest request) { 
        List<Fenlei> flist=this.fenleiService.selectFenleiAll();
        request.setAttribute("flist", flist);
		return "addbook";

	}
	
	//转发forward:/....     重定向:redirect:/...
	@RequestMapping(value="/addBook",method=RequestMethod.POST)
	public String addBook(Book book) {// 添加妖怪
		// System.out.println(monster.toString());
		int i = this.bookService.save(book);
		if (i==1) {
			return "redirect:/showBookHandler/1";
		} else {
			return "forward:/addBookUl";
		}

	}
	//删除图书
	@RequestMapping(value="/deleteBook/{ids}/{pageNow}",method=RequestMethod.DELETE)
	public String delete(@PathVariable(value="ids") String ids,@PathVariable(value = "pageNow") Integer pageNow) { 
           //System.out.println(ids);
		this.bookService.deleteBook(ids);
		return "redirect:/showBookHandler/1";

	}
	
	
	
/*	@RequestMapping(value="/showOneBook/{id}/",method=RequestMethod.GET)
   public String updateUl(@PathVariable(value="monsterId") Integer monsterId,HttpServletRequest request) {// 妖怪

		 
		List<School> slist = this.schoolService.slist();
		request.setAttribute("slist", slist);
		Monster  m = this.monsterService.showOne(monsterId);
		// System.out.println(list.toString());
		request.setAttribute("m", m);
		return "update";

	}*/
}
