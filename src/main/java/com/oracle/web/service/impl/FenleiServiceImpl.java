package com.oracle.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.web.bean.Fenlei;
import com.oracle.web.bean.FenleiExample;
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

}
