package com.oracle.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oracle.web.bean.Book;
import com.oracle.web.bean.BookAndFenlei;
import com.oracle.web.bean.BookExample;
import com.oracle.web.bean.PageBean;
import com.oracle.web.mapper.BookMapper;
import com.oracle.web.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper bookMapper;
	
	@Override
	@Transactional(readOnly=true)
	public PageBean<BookAndFenlei> showBookPesgeAll(Integer pageNow) {
		// TODO Auto-generated method stub
		 PageBean<BookAndFenlei> pb=new PageBean<BookAndFenlei>();
		 PageHelper.startPage(pageNow,5);
		 List<BookAndFenlei> list =this.bookMapper.selectBookAll();
	     PageInfo<BookAndFenlei> page=new PageInfo<BookAndFenlei>(list);
	     pb.setBeanList(list);
	     pb.setPageSize(page.getPageSize());
	     pb.setCounts((int) page.getTotal());//page.getTotal()查询总数
	     pb.setpageNow(page.getPageNum());
	    return pb;
	}

}
