package com.oracle.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oracle.web.bean.BookAndFenlei;
import com.oracle.web.bean.BookTime;
import com.oracle.web.bean.PageBean;
import com.oracle.web.bean.User;
import com.oracle.web.mapper.BookMapper;
import com.oracle.web.mapper.BookTimeMapper;
import com.oracle.web.mapper.UserMapper;
import com.oracle.web.service.UserForegroundService;

@Service
public class UserForegroundServiceImpl implements UserForegroundService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private BookTimeMapper bookTimeMapper;
	
	@Autowired
	private BookMapper bookMapper;
	// 用户登录/注册校验
	@Override
	@Transactional
	public User UserLoginYanZheng(String username) {
		// TODO Auto-generated method stub
		return this.userMapper.UserLoginYanZheng(username);
	}

	// 用户待归还图书
	@Override
	@Transactional
	public PageBean<BookAndFenlei> showguihuan(Integer pageNow, Integer uid) {
		// TODO Auto-generated method stub
		PageBean<BookAndFenlei> pb = new PageBean<BookAndFenlei>();
		PageHelper.startPage(pageNow, 6);
		List<BookAndFenlei> list = this.bookTimeMapper.showguihuan(uid);
		SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		for (BookAndFenlei bt : list) {
			String time = bt.getBt().getTime();

			try {// 日期处理
				c.setTime(ss.parse(time));
				c.add(Calendar.DATE, 30);// 借书时间加30天
				String huan1 = ss.format(c.getTime());
				bt.setHtime(huan1);
				long intervalMilli = c.getTime().getTime() - date.getTime();// 还书时间减去当前日期==剩余归还天数
				Integer date1 = (int) (intervalMilli / (24 * 60 * 60 * 1000));
				bt.setDate(date1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		PageInfo<BookAndFenlei> page = new PageInfo<BookAndFenlei>(list);
		pb.setBeanList(list);
		pb.setPageSize(page.getPageSize());
		pb.setCounts((int) page.getTotal());// page.getTotal()查询总数
		pb.setPageNow(page.getPageNum());
		return pb;

	}

	@Override
	@Transactional
	public int jieshu(Integer uid, Integer bid) {
		// TODO Auto-generated method stub
		BookTime bt=new BookTime();
		bt.setUserid(uid);
		bt.setBookid(bid);
		Date date = new Date();//当前日期
		SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd");
		bt.setTime(ss.format(date));
	    int i = this.bookTimeMapper.insertSelective(bt);
		return i;
	}

	@Override
	@Transactional
	public int bstockUpdateG(Integer bid) {
		// TODO Auto-generated method stub
		
		return  this.bookMapper.bstockUpdateG(bid);
	}

	@Override
	public int huanshu(Integer btid) {
		// TODO Auto-generated method stub
		return this.bookTimeMapper.deleteByPrimaryKey(btid);
	}

	@Override
	public int bstockUpdateH(Integer bid) {
		// TODO Auto-generated method stub
		return this.bookMapper.bstockUpdateH(bid);
	}

}
