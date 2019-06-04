package com.oracle.web.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oracle.web.bean.Book;
import com.oracle.web.bean.BookAndFenlei;
import com.oracle.web.bean.BookExample;
import com.oracle.web.bean.BookExample.Criteria;
import com.oracle.web.bean.PageBean;
import com.oracle.web.mapper.BookMapper;
import com.oracle.web.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper bookMapper;

	@Override // 全查分页图书
	@Transactional(readOnly = true)
	public PageBean<BookAndFenlei> showBookPesgeAll(Integer pageNow) {
		// TODO Auto-generated method stub
		PageBean<BookAndFenlei> pb = new PageBean<BookAndFenlei>();
		PageHelper.startPage(pageNow, 5);
		List<BookAndFenlei> list = this.bookMapper.selectBookAll();
		PageInfo<BookAndFenlei> page = new PageInfo<BookAndFenlei>(list);
		pb.setBeanList(list);
		pb.setPageSize(page.getPageSize());
		pb.setCounts((int) page.getTotal());// page.getTotal()查询总数
		pb.setPageNow(page.getPageNum());
		return pb;
	}

	@Override // 高级搜索图书
	@Transactional(readOnly = true)
	public PageBean<BookAndFenlei> showBookPesgeGaoJi(Integer pageNow, Book book) {
		// TODO Auto-generated method stub
		PageBean<BookAndFenlei> pb = new PageBean<BookAndFenlei>();
		PageHelper.startPage(pageNow, 5);
	 
		System.out.println(book.toString());
		// bname; money; press; author;stock;
 
		List<BookAndFenlei> list = this.bookMapper.selectByGji(book);
		PageInfo<BookAndFenlei> page = new PageInfo<BookAndFenlei>(list);
		pb.setBeanList(list);
		pb.setPageSize(page.getPageSize());
		pb.setCounts((int) page.getTotal());// page.getTotal()查询总数
		pb.setPageNow(page.getPageNum());
		return pb;
	}

	// 删除图书，根据id删除，多选删除
	@Override
	@Transactional
	public void deleteBook(String ids) {
		// TODO Auto-generated method stub
		String[] a = ids.split(",");

		Integer[] values = new Integer[a.length];
		for (int i = 0; i < a.length; i++) {

			values[i] = Integer.parseInt(a[i]);
		}
		// List<Integer> asList = Arrays.asList(values);

		BookExample example = new BookExample();
		Criteria criteria = example.createCriteria();
		criteria.andBidIn(Arrays.asList(values));
		this.bookMapper.deleteByExample(example);
	}

	// 添加,修改图书校验
	@Override
	@Transactional
	public Book yanzhengAddBook(String bname, String flid) {
		// TODO Auto-generated method stub
		BookExample example = new BookExample();
		Criteria criteria = example.createCriteria();
		criteria.andFlidEqualTo(Integer.parseInt(flid));
		criteria.andBnameEqualTo(bname);
		List<Book> list = this.bookMapper.selectByExample(example);
		Book book = null;
		for (Book book1 : list) {
			book = book1;
		}
		// System.out.println(book);
		return book;
	}

	// 添加图书
	@Override
	@Transactional
	public int save(Book book) {
		// TODO Auto-generated method stub
		return this.bookMapper.insert(book);
	}

	// 修改图书页面分类名查询
	@Override
	@Transactional
	public Book updateBookUl(Integer id) {
		// TODO Auto-generated method stub

		return this.bookMapper.selectByPrimaryKey(id);
	}

	// 修改图书
	@Override
	@Transactional
	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		this.bookMapper.updateByPrimaryKey(book);
	}

	@Override
	@Transactional
	public List<BookAndFenlei> outPutBookAll() {
		// TODO Auto-generated method stub
		return this.bookMapper.selectBookAll();
	}

	@Override
	@Transactional
	public List<BookAndFenlei> outPutBookIds(String ids1) {
		// TODO Auto-generated method stub
		String[] a = ids1.split(",");
 
		 List<Integer> list =new ArrayList<Integer>();
		 for (int i = 0; i < a.length; i++) {
	           
				list.add(Integer.parseInt(a[i]));
				 
			}
		return this.bookMapper.selectOutPutIds(list);
	}

}
