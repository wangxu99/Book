package com.oracle.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.web.mapper.AdminMapper;
import com.oracle.web.bean.Admin;
import com.oracle.web.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {


	@Autowired
	private AdminMapper adminMapper;

	@Override
	@Transactional
	public int save(Admin admin) {
		// TODO Auto-generated method stub
		return this.adminMapper.insertSelective(admin);
	}

	
	@Override
	@Transactional(readOnly=true)
	public Admin login(String asername) {
		// TODO Auto-generated method stub
		return this.adminMapper.loginYanZheng(asername);
	}
	


	@Override
	@Transactional(readOnly=true)
	public Admin showAdmin(String aname) {
		// TODO Auto-generated method stub
		return this.adminMapper.loginYanZheng(aname);
	}


	@Override
	@Transactional
	public int updatePassword(int i, String newpassword) {
		
		Admin a=new Admin();
		a.setAid(i);
		a.setPassword(newpassword);
		return this.adminMapper.updateByPrimaryKeySelective(a);
	}


 
	
}
