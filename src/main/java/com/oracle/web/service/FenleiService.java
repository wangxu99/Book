package com.oracle.web.service;

import java.util.List;

import com.oracle.web.bean.Fenlei;
import com.oracle.web.bean.PageBean;
 


public interface FenleiService {

	List<Fenlei> selectFenleiAll();

	List<Fenlei> list();

	int save(Fenlei fenlei);

	int delete(Fenlei f);

	Fenlei selectByPrimaryKey(Integer id);

	int update(Fenlei fenlei);

	PageBean<Fenlei> selectAllByPageHelper(Integer pageNow);

	int counts();

	Fenlei yanzhengAddFenlei(String fname);

	int yanzhengAddFenlei2(Integer fid);

	List<Fenlei> outPutFenLeiAll();

	List<Fenlei> outPutFenleiIds(String ids1);

	//Fenlei yanzhengAddFenlei(String fname, String fid);

	 

	 
 




}
