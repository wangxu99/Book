package com.oracle.web.mapper;

import com.oracle.web.bean.Book;
import com.oracle.web.bean.BookAndFenlei;
import com.oracle.web.bean.BookExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BookMapper {
    long countByExample(BookExample example);

    int deleteByExample(BookExample example);

    int deleteByPrimaryKey(Integer bid);

    int insert(Book record);

    int insertSelective(Book record);

    List<Book> selectByExample(BookExample example);
    
    Book selectByPrimaryKey(Integer bid);

    int updateByExampleSelective(@Param("record") Book record, @Param("example") BookExample example);

    int updateByExample(@Param("record") Book record, @Param("example") BookExample example);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

	List<BookAndFenlei> selectBookAll();//自定义全查分页
	   
    List<BookAndFenlei> selectByGji(Book book);//自己定义高级搜索
    
    List<BookAndFenlei> selectOutPutIds(List<Integer> list);
 
}