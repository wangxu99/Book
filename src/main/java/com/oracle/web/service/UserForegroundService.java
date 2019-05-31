package com.oracle.web.service;

import com.oracle.web.bean.BookAndFenlei;
 
import com.oracle.web.bean.PageBean;
import com.oracle.web.bean.User;

public interface UserForegroundService {
	User UserLoginYanZheng(String username);

	PageBean<BookAndFenlei> showguihuan(Integer pageNow, Integer uid);
}
