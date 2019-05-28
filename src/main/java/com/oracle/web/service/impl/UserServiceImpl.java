package com.oracle.web.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.web.bean.Book;
import com.oracle.web.bean.BookExample;
import com.oracle.web.bean.PageBean;
import com.oracle.web.bean.User;
import com.oracle.web.bean.UserExample;
import com.oracle.web.bean.UserExample.Criteria;
import com.oracle.web.mapper.UserMapper;
import com.oracle.web.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	// 分页查
	@Override
	@Transactional(readOnly = true)
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

	
	//添加
	@Override
	@Transactional
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return this.userMapper.insert(user);
	}

	
	// 批量删
	@Override
	@Transactional
	public void deleteUser(String ids) {
		// TODO Auto-generated method stub
		
		String[] a = ids.split(",");

		Integer[] values = new Integer[a.length];
		for (int i = 0; i < a.length; i++) {

			values[i] = Integer.parseInt(a[i]);
		}
		
		UserExample example = new UserExample();
		
		Criteria criteria = example.createCriteria();
		
		criteria.andUidIn(Arrays.asList(values));
		
		this.userMapper.deleteByExample(example);	
	}


	@Override
	@Transactional
	public User selectOne(Integer id) {
		// TODO Auto-generated method stub
		
		return this.userMapper.selectByPrimaryKey(id);
	}

 
	//用户登录/注册校验--wx
	@Override
	@Transactional
	public User UserLoginYanZheng(String username) {
		// TODO Auto-generated method stub
		return  this.userMapper.UserLoginYanZheng(username);
	}

}
