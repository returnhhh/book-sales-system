package com.ruoyi.mapper;

import com.ruoyi.domain.Book;
import org.apache.ibatis.annotations.Mapper;

/**
 * 图书数据Mapper接口
 *
 * @author 智慧的小国
 * @date 2024-06-06
 */
@Mapper
public interface BookMapper {

    /**
     * 添加书籍信息
     * @param book
     * @return
     */
    public Integer addBook(Book book);
}
