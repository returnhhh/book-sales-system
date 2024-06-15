package com.ruoyi.orderManagement.service;

import java.util.List;
import com.ruoyi.orderManagement.domain.Order;

/**
 * 订单管理Service接口
 * 
 * @author 智慧的小国
 * @date 2024-06-11
 */
public interface IOrderService 
{
    /**
     * 查询订单管理
     * 
     * @param orderId 订单管理主键
     * @return 订单管理
     */
    public Order selectOrderByOrderId(String orderId);

    /**
     * 查询订单管理列表
     * 
     * @param order 订单管理
     * @return 订单管理集合
     */
    public List<Order> selectOrderList(Order order);

    /**
     * 本周中每天已支付订单的数量统计
     *
     * @param order 订单分析
     * @return 订单数据分析
     */
    public List<Order> selectOrderListAnalysis(Order order);

    /**
     * 本周中销量最高的前五本书
     *
     * @param order 订单分析
     * @return 订单数据分析
     */
    public List<Order> selectOrderListAnalysis1(Order order);

    /**
     * 新增订单管理
     * 
     * @param order 订单管理
     * @return 结果
     */
    public int insertOrder(Order order);

    /**
     * 修改订单管理
     * 
     * @param order 订单管理
     * @return 结果
     */
    public int updateOrder(Order order);

    /**
     * 批量删除订单管理
     * 
     * @param orderIds 需要删除的订单管理主键集合
     * @return 结果
     */
    public int deleteOrderByOrderIds(String[] orderIds);

    /**
     * 删除订单管理信息
     * 
     * @param orderId 订单管理主键
     * @return 结果
     */
    public int deleteOrderByOrderId(String orderId);
}
