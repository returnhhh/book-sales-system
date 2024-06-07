package com.ruoyi.books.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.books.mapper.BooksMapper;
import com.ruoyi.books.domain.Books;
import com.ruoyi.books.service.IBooksService;

/**
 * 图书商城信息Service业务层处理
 * 
 * @author 智慧的小国
 * @date 2024-06-07
 */
@Service
public class BooksServiceImpl implements IBooksService 
{
    @Autowired
    private BooksMapper booksMapper;

    /**
     * 查询图书商城信息
     * 
     * @param bookId 图书商城信息主键
     * @return 图书商城信息
     */
    @Override
    public Books selectBooksByBookId(String bookId)
    {
        return booksMapper.selectBooksByBookId(bookId);
    }

    /**
     * 查询图书商城信息列表
     * 
     * @param books 图书商城信息
     * @return 图书商城信息
     */
    @Override
    public List<Books> selectBooksList(Books books)
    {
        return booksMapper.selectBooksList(books);
    }

    /**
     * 新增图书商城信息
     * 
     * @param books 图书商城信息
     * @return 结果
     */
    @Override
    public int insertBooks(Books books)
    {
        books.setCreateTime(DateUtils.getNowDate());
        return booksMapper.insertBooks(books);
    }

    /**
     * 修改图书商城信息
     * 
     * @param books 图书商城信息
     * @return 结果
     */
    @Override
    public int updateBooks(Books books)
    {
        return booksMapper.updateBooks(books);
    }

    /**
     * 批量删除图书商城信息
     * 
     * @param bookIds 需要删除的图书商城信息主键
     * @return 结果
     */
    @Override
    public int deleteBooksByBookIds(String[] bookIds)
    {
        return booksMapper.deleteBooksByBookIds(bookIds);
    }

    /**
     * 删除图书商城信息信息
     * 
     * @param bookId 图书商城信息主键
     * @return 结果
     */
    @Override
    public int deleteBooksByBookId(String bookId)
    {
        return booksMapper.deleteBooksByBookId(bookId);
    }
}
