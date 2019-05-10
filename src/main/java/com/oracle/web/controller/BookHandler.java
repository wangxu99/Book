package com.oracle.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.web.bean.Book;
import com.oracle.web.bean.BookAndFenlei;
import com.oracle.web.bean.PageBean;
import com.oracle.web.service.BookService;

@Controller
@Scope(value = "prototype")
public class BookHandler {

	@Autowired
	private BookService bookService;

	//全查分页
	@RequestMapping(value = "/showBookHandler/{pageNow}", method = RequestMethod.GET)
	public String showPasgeMonster(@PathVariable(value = "pageNow") Integer pageNow, HttpServletRequest request) {// 分页查看
		//System.out.println("访问页数" + pageNow);
		/*
		 * if (pageNow == null || pageNow < 1) { pageNow = 1; }
		 */
		PageBean<BookAndFenlei> pb = bookService.showBookPesgeAll(pageNow);
          List<BookAndFenlei> list=pb.getBeanList();
          for (BookAndFenlei bookAndFenlei : list) {
			System.out.println(bookAndFenlei.toString());
		}
		request.setAttribute("pb", pb);
		request.setAttribute("showPesge", "showBook");
		

		return "showBook";

	}
}
