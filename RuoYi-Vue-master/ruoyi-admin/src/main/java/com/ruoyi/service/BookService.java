package com.ruoyi.service;

import com.ruoyi.domain.Book;

/**
 * 图书数据Service接口
 *
 * @author 智慧的小国
 * @date 2024-06-06
 */
public interface BookService {

    /**
     * 添加书籍信息
     * @param book
     * @return
     */
    public Integer addBook(Book book);
}
