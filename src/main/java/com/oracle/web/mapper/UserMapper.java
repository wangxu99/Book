package com.oracle.web.mapper;

import com.oracle.web.bean.User;
import com.oracle.web.bean.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    // 批量删除
    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer uid);

    // 添加
    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    // 修改 先查出
    User selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

  //修改
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	int selectCount();

	List<User> showByPage(int index);

	User selectValidate(String username);

	//导出选中
	List<User> selectOutPutIds(List<Integer> list);

	//导出全部
	List<User> selectOutPutAll();
}