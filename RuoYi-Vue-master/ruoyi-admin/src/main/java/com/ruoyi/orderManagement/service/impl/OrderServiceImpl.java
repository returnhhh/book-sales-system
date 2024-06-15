package com.ruoyi.orderManagement.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.ruoyi.SnowFlakeUtil.java.SnowFlakeUtil;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.orderManagement.mapper.OrderMapper;
import com.ruoyi.orderManagement.domain.Order;
import com.ruoyi.orderManagement.service.IOrderService;

import static com.ruoyi.common.utils.SecurityUtils.getUsername;

/**
 * 订单管理Service业务层处理
 * 
 * @author 智慧的小国
 * @date 2024-06-11
 */
@Service
public class OrderServiceImpl implements IOrderService 
{
    @Autowired
    private OrderMapper orderMapper;

    SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil();

    /**
     * 查询订单管理
     * 
     * @param orderId 订单管理主键
     * @return 订单管理
     */
    @Override
    public Order selectOrderByOrderId(String orderId)
    {
        return orderMapper.selectOrderByOrderId(orderId);
    }

    /**
     * 查询订单管理列表
     * 
     * @param order 订单管理
     * @return 订单管理
     */
    @Override
    public List<Order> selectOrderList(Order order)
    {
        return orderMapper.selectOrderList(order);
    }

    /**
     * 本周中每天已支付订单的数量统计
     *
     * @param order 订单分析
     * @return 订单数据分析
     */
    @Override
    public List<Order> selectOrderListAnalysis(Order order)
    {
        return orderMapper.selectOrderListAnalysis(order);
    }

    /**
     * 本周中销量最高的前五本书
     *
     * @param order 订单分析
     * @return 订单数据分析
     */
    public List<Order> selectOrderListAnalysis1(Order order)
    {
        return orderMapper.selectOrderListAnalysis1(order);
    }

    /**
     * 新增订单管理
     * 
     * @param order 订单管理
     * @return 结果
     */
    @Override
    public int insertOrder(Order order)
    {
        order.setOrderId(String.valueOf(snowFlakeUtil.nextId()));
        order.setUserName(getUsername());
        order.setCreateDate(LocalDateTime.now());
        order.setAllPrice(order.getPrice() * order.getNum());
        return orderMapper.insertOrder(order);
    }

    /**
     * 修改订单管理
     * 
     * @param order 订单管理
     * @return 结果
     */
    @Override
    public int updateOrder(Order order)
    {
        order.setUpdateDate(LocalDateTime.now());
        return orderMapper.updateOrder(order);
    }

    /**
     * 批量删除订单管理
     * 
     * @param orderIds 需要删除的订单管理主键
     * @return 结果
     */
    @Override
    public int deleteOrderByOrderIds(String[] orderIds)
    {
        return orderMapper.deleteOrderByOrderIds(orderIds);
    }

    /**
     * 删除订单管理信息
     * 
     * @param orderId 订单管理主键
     * @return 结果
     */
    @Override
    public int deleteOrderByOrderId(String orderId)
    {
        return orderMapper.deleteOrderByOrderId(orderId);
    }
}
