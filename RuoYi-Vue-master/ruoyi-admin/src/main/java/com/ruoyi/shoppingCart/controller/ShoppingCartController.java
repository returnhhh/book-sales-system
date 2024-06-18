package com.ruoyi.shoppingCart.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.books.domain.Books;
import com.ruoyi.books.service.IBooksService;
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
import com.ruoyi.shoppingCart.domain.ShoppingCart;
import com.ruoyi.shoppingCart.service.IShoppingCartService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 购物车信息Controller
 * 
 * @author 智慧的小国
 * @date 2024-06-12
 */
@RestController
@RequestMapping("/shoppingCart/cart")
public class ShoppingCartController extends BaseController
{
    @Autowired
    private IShoppingCartService shoppingCartService;

    @Autowired
    private IBooksService booksService;
    /**
     * 查询购物车信息列表
     */
//    @PreAuthorize("@ss.hasPermi('shoppingCart:cart:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShoppingCart shoppingCart)
    {
        startPage();
        List<ShoppingCart> list = shoppingCartService.selectShoppingCartList(shoppingCart);
        return getDataTable(list);
    }

    /**
     * 导出购物车信息列表
     */
//    @PreAuthorize("@ss.hasPermi('shoppingCart:cart:export')")
    @Log(title = "购物车信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShoppingCart shoppingCart)
    {
        List<ShoppingCart> list = shoppingCartService.selectShoppingCartList(shoppingCart);
        ExcelUtil<ShoppingCart> util = new ExcelUtil<ShoppingCart>(ShoppingCart.class);
        util.exportExcel(response, list, "购物车信息数据");
    }

    /**
     * 获取购物车信息详细信息
     */
//    @PreAuthorize("@ss.hasPermi('shoppingCart:cart:query')")
    @GetMapping(value = "/{scId}")
    public AjaxResult getInfo(@PathVariable("scId") String scId)
    {
        return success(shoppingCartService.selectShoppingCartByScId(scId));
    }

    /**
     * 新增购物车信息
     */
//    @PreAuthorize("@ss.hasPermi('shoppingCart:cart:add')")
    @Log(title = "购物车信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShoppingCart shoppingCart)
    {
        System.out.println(shoppingCart);
        Long num=shoppingCart.getNum();
        String bookId=shoppingCart.getBookId();
        Books book= booksService.selectBooksByBookId(bookId);
        Long maxNum=book.getBookNum();
        if(num<maxNum){
        return toAjax(shoppingCartService.insertShoppingCart(shoppingCart));}
        else{
            return new AjaxResult(201,"库存数量不足");
        }
    }

    /**
     * 修改购物车信息
     */
//    @PreAuthorize("@ss.hasPermi('shoppingCart:cart:edit')")
    @Log(title = "购物车信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShoppingCart shoppingCart)
    {
        return toAjax(shoppingCartService.updateShoppingCart(shoppingCart));
    }

    /**
     * 删除购物车信息
     */
//    @PreAuthorize("@ss.hasPermi('shoppingCart:cart:remove')")
    @Log(title = "购物车信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scIds}")
    public AjaxResult remove(@PathVariable String[] scIds)
    {
        return toAjax(shoppingCartService.deleteShoppingCartByScIds(scIds));
    }
}
