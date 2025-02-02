package com.ruoyi.orderManagement.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.books.domain.Books;
import com.ruoyi.books.service.IBooksService;
import com.ruoyi.orderManagement.domain.UpdateOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.orderManagement.domain.Order;
import com.ruoyi.orderManagement.service.IOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单管理Controller
 * 
 * @author 智慧的小国
 * @date 2024-06-11
 */
@RestController
@RequestMapping("/orderManagement/order")
public class OrderController extends BaseController
{
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IBooksService booksService;

    /**
     * 查询订单管理列表
     */
//    @PreAuthorize("@ss.hasPermi('orderManagement:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(Order order)
    {
        startPage();
        List<Order> list = orderService.selectOrderList(order);
        return getDataTable(list);
    }

    /**
     * 本周中每天已支付订单的数量统计
     */
//    @PreAuthorize("@ss.hasPermi('orderManagement:order:list')")
    @GetMapping("/listAnalysis")
    public AjaxResult listAnalysis(Order order)
    {
        List<Order> list = orderService.selectOrderListAnalysis(order);
        System.out.println(list);
        return success(list);
    }

    /**
     * 本周中销量最高的前五本书
     */
//    @PreAuthorize("@ss.hasPermi('orderManagement:order:list')")
    @GetMapping("/listAnalysis1")
    public AjaxResult listAnalysis1(Order order)
    {
        List<Order> list = orderService.selectOrderListAnalysis1(order);
        return success(list);
    }

    /**
     * 导出订单管理列表
     */
//    @PreAuthorize("@ss.hasPermi('orderManagement:order:export')")
    @Log(title = "订单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Order order)
    {
        List<Order> list = orderService.selectOrderList(order);
        ExcelUtil<Order> util = new ExcelUtil<Order>(Order.class);
        util.exportExcel(response, list, "订单管理数据");
    }

    /**
     * 获取订单管理详细信息
     */
//    @PreAuthorize("@ss.hasPermi('orderManagement:order:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") String orderId)
    {
        return success(orderService.selectOrderByOrderId(orderId));
    }

    /**
     * 新增订单管理
     */
//    @PreAuthorize("@ss.hasPermi('orderManagement:order:add')")
    @Log(title = "订单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Order order)
    {
        return toAjax(orderService.insertOrder(order));
    }

    /**
     * 修改订单管理
     */
//    @PreAuthorize("@ss.hasPermi('orderManagement:order:edit')")
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Order order)
    {
        return toAjax(orderService.updateOrder(order));
    }

    /**
     * 删除订单管理
     */
//    @PreAuthorize("@ss.hasPermi('orderManagement:order:remove')")
    @Log(title = "订单管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable String[] orderIds)
    {
        return toAjax(orderService.deleteOrderByOrderIds(orderIds));
    }

    /**
     * 修改支付状态
     */
    @PostMapping ("/updateState")
    public AjaxResult updateState(@RequestBody UpdateOrder updateOrder)
    {

        String orderId = updateOrder.getOrderId();
        Order order = orderService.selectOrderByOrderId(orderId);
        Long num= order.getNum();
        String bookId = order.getBookId();
        Books book = booksService.selectBooksByBookId(bookId);
        Long maxNum = book.getBookNum();
        if(order.getState().equals("已支付")){
            return new AjaxResult(201,"支付重复，无法进行重复支付");
        } else if (num>maxNum) {
            return new AjaxResult(201,"库存数量不足");
        } else {
            return toAjax(orderService.updateState(updateOrder));
        }
    }

}
