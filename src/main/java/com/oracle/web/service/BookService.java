package com.oracle.web.service;

import java.util.List;

import com.oracle.web.bean.Book;
import com.oracle.web.bean.BookAndFenlei;
import com.oracle.web.bean.PageBean;

public interface BookService {  

	PageBean<BookAndFenlei> showBookPesgeAll(Integer pageNow);

	void deleteBook(String ids);

	Book yanzhengAddBook(String bname, String flid);

	int save(Book book);

	Book updateBookUl(Integer id);

	void updateBook(Book book);

	PageBean<BookAndFenlei> showBookPesgeGaoJi(Integer pageNow, Book book);

	List<BookAndFenlei> outPutBookAll();

	List<BookAndFenlei> outPutBookIds(String ids1);

	 
	

 

 

}
