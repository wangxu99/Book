package com.oracle.web.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oracle.web.bean.BookAndFenlei;
import com.oracle.web.bean.PageBean;
import com.oracle.web.bean.User;
import com.oracle.web.mapper.BookTimeMapper;
import com.oracle.web.mapper.UserMapper;
import com.oracle.web.service.UserForegroundService;

 

@Service
public class UserForegroundServiceImpl implements UserForegroundService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
 private BookTimeMapper bookTimeMapper;
	//用户登录/注册校验
	@Override
	@Transactional
	public User UserLoginYanZheng(String username) {
		// TODO Auto-generated method stub
		return  this.userMapper.UserLoginYanZheng(username);
	}

	//用户待归还图书
	@Override
	@Transactional
	public PageBean<BookAndFenlei> showguihuan(Integer pageNow, Integer uid) {
	 	// TODO Auto-generated method stub
		PageBean<BookAndFenlei> pb = new PageBean<BookAndFenlei>();
		PageHelper.startPage(pageNow, 5);
		List<BookAndFenlei> list = this.bookTimeMapper.showguihuan(uid);
		PageInfo<BookAndFenlei> page = new PageInfo<BookAndFenlei>(list);
		pb.setBeanList(list);
		pb.setPageSize(page.getPageSize());
		pb.setCounts((int) page.getTotal());// page.getTotal()查询总数
		pb.setPageNow(page.getPageNum());
		return pb; 
	 
	}
 

}
