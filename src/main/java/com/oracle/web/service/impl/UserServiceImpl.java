package com.oracle.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.oracle.web.bean.PageBean;
import com.oracle.web.bean.User;
import com.oracle.web.mapper.UserMapper;
import com.oracle.web.service.UserService;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public PageBean<User> showByPage(Integer pageNow) {
		// TODO Auto-generated method stub

		PageBean<User> pb = new PageBean<User>();
		
		int pageSize = 8;

		pb.setPageNow(pageNow);

		pb.setPageSize(pageSize);
		
		//查看有多少条
		int sum=userMapper.selectCount();
		
		pb.setCounts(sum);
		
		//从第几条开始
		int index=(pageNow-1)*pageSize;
		
		List<User> list=this.userMapper.showByPage(index);
		
		pb.setBeanList(list);
		
		return pb;
	}

}
