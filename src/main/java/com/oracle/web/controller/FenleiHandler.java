package com.oracle.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.web.bean.Book;
import com.oracle.web.service.BookService;

@Controller
@Scope(value = "prototype")
public class FenleiHandler {

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String save(HttpServletRequest request) {

		//List<Book> list = bookService.list();

		//request.setAttribute("blist", list);

		return "add";

	}
	
}
