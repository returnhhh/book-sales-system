package com.ruoyi.books.mapper;

import java.util.List;
import com.ruoyi.books.domain.Books;

/**
 * 图书商城信息Mapper接口
 * 
 * @author 智慧的小国
 * @date 2024-06-07
 */
public interface BooksMapper 
{
    /**
     * 查询图书商城信息
     * 
     * @param bookId 图书商城信息主键
     * @return 图书商城信息
     */
    public Books selectBooksByBookId(String bookId);

    /**
     * 查询图书商城信息列表
     * 
     * @param books 图书商城信息
     * @return 图书商城信息集合
     */
    public List<Books> selectBooksList(Books books);

    /**
     * 新增图书商城信息
     * 
     * @param books 图书商城信息
     * @return 结果
     */
    public int insertBooks(Books books);

    /**
     * 修改图书商城信息
     * 
     * @param books 图书商城信息
     * @return 结果
     */
    public int updateBooks(Books books);

    /**
     * 删除图书商城信息
     * 
     * @param bookId 图书商城信息主键
     * @return 结果
     */
    public int deleteBooksByBookId(String bookId);

    /**
     * 批量删除图书商城信息
     * 
     * @param bookIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBooksByBookIds(String[] bookIds);
}
