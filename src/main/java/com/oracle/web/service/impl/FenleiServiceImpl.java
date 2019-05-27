package com.oracle.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oracle.web.bean.Fenlei;
import com.oracle.web.bean.FenleiExample;
import com.oracle.web.bean.PageBean;
import com.oracle.web.mapper.FenleiMapper;
import com.oracle.web.service.FenleiService;
 
@Service
public class FenleiServiceImpl implements FenleiService {

	@Autowired
	private FenleiMapper fenleiMapper;

	 
 
	@Override
	@Transactional
	public List<Fenlei> selectFenleiAll() {
		// TODO Auto-generated method stub
		 FenleiExample example=new FenleiExample();
		return this.fenleiMapper.selectByExample(example);
	}



	@Override
	@Transactional
	public List<Fenlei> list() {
		// TODO Auto-generated method stub
		FenleiExample example=new FenleiExample();
		return this.fenleiMapper.selectByExample(example);
	}



	@Override
	@Transactional
	public int save(Fenlei fenlei) {
		// TODO Auto-generated method stub
		return this.fenleiMapper.insert(fenlei);
	}



	@Override
	@Transactional
	public int delete(Fenlei f) {
		// TODO Auto-generated method stub
		return this.fenleiMapper.deleteByPrimaryKey(f.getFid());
	}



	@Override
	@Transactional
	public Fenlei selectByPrimaryKey(Integer fid) {
		// TODO Auto-generated method stub
		return this.fenleiMapper.selectByPrimaryKey(fid);
	}



	@Override
	@Transactional
	public int update(Fenlei fenlei) {
		// TODO Auto-generated method stub
		return this.fenleiMapper.updateByPrimaryKey(fenlei);
	}



	@Override
	public PageBean<Fenlei> selectAllByPageHelper(Integer pageNow) {
		// TODO Auto-generated method stub
		PageBean<Fenlei> pb = new PageBean<Fenlei>();


		// 当前页的数据
		PageHelper.startPage(pageNow,5);


		//已经是分页好的数据了
		List<Fenlei> list = this.fenleiMapper.selectAllByPageHelper();


		pb.setBeanList(list);
		
		//总记录数
		PageInfo<Fenlei> pi=new PageInfo<Fenlei>(list);
		
		pb.setCounts((int)pi.getTotal());
		
		// 当前页
		//pb.setPageNow(pageNow);
		pb.setPageNow(pi.getPageNum());


		// 每页显示的条数-自定义
		//pb.setPageSize(3);
		pb.setPageSize(pi.getPageSize());


		return pb;
	}


	@Override
	@Transactional
	public int counts() {
		// TODO Auto-generated method stub
		return this.fenleiMapper.counts();
	}


 

}
