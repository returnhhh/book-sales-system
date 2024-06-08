package com.ruoyi.books.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.books.domain.Books;
import com.ruoyi.books.service.IBooksService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 图书商城信息Controller
 * 
 * @author 智慧的小国
 * @date 2024-06-07
 */
@RestController
@RequestMapping("/books/books")
public class BooksController extends BaseController
{
    @Autowired
    private IBooksService booksService;

    /**
     * 查询图书商城信息列表
     */
    @PreAuthorize("@ss.hasPermi('books:books:list')")
    @GetMapping("/list")
    public TableDataInfo list(Books books)
    {
        startPage();
        List<Books> list = booksService.selectBooksList(books);
        System.out.println(list);
        return getDataTable(list);
    }

    /**
     * 导出图书商城信息列表
     */
    @PreAuthorize("@ss.hasPermi('books:books:export')")
    @Log(title = "图书商城信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Books books)
    {
        List<Books> list = booksService.selectBooksList(books);
        ExcelUtil<Books> util = new ExcelUtil<Books>(Books.class);
        util.exportExcel(response, list, "图书商城信息数据");
    }

    /**
     * 获取图书商城信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('books:books:query')")
    @GetMapping(value = "/{bookId}")
    public AjaxResult getInfo(@PathVariable("bookId") String bookId)
    {
        return success(booksService.selectBooksByBookId(bookId));
    }

    /**
     * 新增图书商城信息
     */
    @PreAuthorize("@ss.hasPermi('books:books:add')")
    @Log(title = "图书商城信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Books books)
    {
        books.setCreateBy(getUsername());
        books.setCreateDate(LocalDateTime.now());
        return toAjax(booksService.insertBooks(books));
    }

    /**
     * 修改图书商城信息
     */
    @PreAuthorize("@ss.hasPermi('books:books:edit')")
    @Log(title = "图书商城信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Books books)
    {
        books.setUpdateBy(getUsername());
        books.setUpdateDate(LocalDateTime.now());
        return toAjax(booksService.updateBooks(books));
    }

    /**
     * 删除图书商城信息
     */
    @PreAuthorize("@ss.hasPermi('books:books:remove')")
    @Log(title = "图书商城信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bookIds}")
    public AjaxResult remove(@PathVariable String[] bookIds)
    {
        return toAjax(booksService.deleteBooksByBookIds(bookIds));
    }
}
