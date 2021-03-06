package com.oracle.web.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


	//单查
	@Override
	@Transactional
	public User selectOne(Integer id) {
		// TODO Auto-generated method stub
		
		return this.userMapper.selectByPrimaryKey(id);
	}

 
 

	
	// 修改头像
	@Override
	public void updateTouxoiang(User user) {
		// TODO Auto-generated method stub
		
		this.userMapper.updateByPrimaryKeySelective(user);
	}


	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
		this.userMapper.updateByPrimaryKeySelective(user);
		
	}


	@Override
	public User validateUser(String username) {
		// TODO Auto-generated method stub
		return this.userMapper.selectValidate(username);
	}


	@Override
	public List<User> outPutUserAll() {
		// TODO Auto-generated method stub
		
		return this.userMapper.selectOutPutAll();
	}


	@Override
	public List<User> outPutUserIds(String ids) {
		// TODO Auto-generated method stub
		
		String[] a = ids.split(",");
		 
		 List<Integer> list =new ArrayList<Integer>();
		 for (int i = 0; i < a.length; i++) {
	           
				list.add(Integer.parseInt(a[i]));
				 
			}
		return this.userMapper.selectOutPutIds(list);
	}

}
