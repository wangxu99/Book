package com.oracle.web.service;

import com.oracle.web.bean.PageBean;
import com.oracle.web.bean.User;

public interface UserService {

	PageBean<User> showByPage(Integer pageNow);

	int addUser(User user);

	void deleteUser(String ids);

	User selectOne(Integer id);

	void updateTouxoiang(User user);

	void updateUser(User user);

}
