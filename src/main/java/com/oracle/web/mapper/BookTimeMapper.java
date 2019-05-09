package com.oracle.web.mapper;

import com.oracle.web.bean.BookTime;
import com.oracle.web.bean.BookTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookTimeMapper {
    long countByExample(BookTimeExample example);

    int deleteByExample(BookTimeExample example);

    int deleteByPrimaryKey(Integer btid);

    int insert(BookTime record);

    int insertSelective(BookTime record);

    List<BookTime> selectByExample(BookTimeExample example);

    BookTime selectByPrimaryKey(Integer btid);

    int updateByExampleSelective(@Param("record") BookTime record, @Param("example") BookTimeExample example);

    int updateByExample(@Param("record") BookTime record, @Param("example") BookTimeExample example);

    int updateByPrimaryKeySelective(BookTime record);

    int updateByPrimaryKey(BookTime record);
}