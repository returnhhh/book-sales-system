package com.ruoyi.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.domain.Book;
import com.ruoyi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 图书管理
 *
 * @author 智慧的小国
 * @date 2024-06-06
 */
@RestController
@RequestMapping("book")
public class BookController extends BaseController {

    @Autowired
    BookService bookService;

    /**
     * 添加书籍
     */
    @PostMapping()
    public AjaxResult add(@Valid @RequestBody Book book){
        return toAjax(bookService.addBook(book));
    }
}
