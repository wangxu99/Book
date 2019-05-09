package com.oracle.web.test;


import static org.junit.Assert.*;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.oracle.web.bean.Admin;
import com.oracle.web.bean.AdminExample;
import com.oracle.web.bean.Book;
import com.oracle.web.bean.BookTime;
import com.oracle.web.bean.Fenlei;
import com.oracle.web.bean.User;
import com.oracle.web.mapper.AdminMapper;
import com.oracle.web.mapper.BookMapper;
import com.oracle.web.mapper.BookTimeMapper;
import com.oracle.web.mapper.FenleiMapper;
import com.oracle.web.mapper.UserMapper;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class TestMapper {

	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private BookTimeMapper fenleiMapper;
	
	@Test
	public void testAll() {
		 PageHelper.startPage(1, 3);
		AdminExample example=new AdminExample();
		 
		List<Admin> list=this.adminMapper.selectByExample(example);
		for (Admin admin : list) {
		 System.out.println(admin.toString());
		}
	}
	
	@Test
	public void test1() {
		//PageHelper.startPage(1, 3);
		 
		
		 Admin  list=this.adminMapper.selectByPrimaryKey(1);
		 
			
			System.out.println(list.toString());
	 
	}
	
	
	@Test
	public void test2() {
		//PageHelper.startPage(1, 3);
		 
		
		BookTime  list=this.fenleiMapper.selectByPrimaryKey(1);
		 
			
			System.out.println(list.toString());
	 
	}
	
}
