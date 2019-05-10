package com.oracle.web.service;

import com.oracle.web.bean.Book;
import com.oracle.web.bean.BookAndFenlei;
import com.oracle.web.bean.PageBean;

public interface BookService {

	PageBean<BookAndFenlei> showBookPesgeAll(Integer pageNow);

}
