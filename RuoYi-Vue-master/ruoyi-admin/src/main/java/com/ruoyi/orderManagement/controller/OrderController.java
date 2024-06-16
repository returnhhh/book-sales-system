package com.ruoyi.orderManagement.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

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
import com.ruoyi.orderManagement.service.domain.Order;
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
    @PutMapping("/updateState/{orderId}")
    public AjaxResult updateState(@PathVariable String orderId)
    {
        System.out.println(orderId);
        return toAjax(orderService.updateState(orderId));
    }
    /**
     * 修改库存数量
     */
    @PutMapping("/updateNum/{orderId}")
    public AjaxResult updateNum(@PathVariable String orderId,Long num)
    {
        System.out.println(orderId);
        return toAjax(orderService.updateNum(orderId,num));
    }
}
