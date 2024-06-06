package com.ruoyi.service.impl;

import com.ruoyi.domain.Book;
import com.ruoyi.mapper.BookMapper;
import com.ruoyi.service.BookService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 图书数据Service业务处理
 *
 * @author 智慧的小国
 * @date 2024-06-06
 */
@Service
public class BookImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;
    @Override
    public Integer addBook(Book book) {
        return bookMapper.addBook(book);
    }
}
