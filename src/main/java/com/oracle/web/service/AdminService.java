package com.oracle.web.service;


import com.oracle.web.bean.Admin;

public interface AdminService {

	int save(Admin admin);

	Admin login(String asername);

	Admin showAdmin(String aname);

	int updatePassword(int i, String newpassword);

 

	
}

