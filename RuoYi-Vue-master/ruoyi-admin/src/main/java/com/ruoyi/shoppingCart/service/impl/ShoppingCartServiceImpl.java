package com.ruoyi.shoppingCart.service.impl;

import java.util.List;

import ch.qos.logback.core.subst.Token;
import com.ruoyi.SnowFlakeUtil.java.SnowFlakeUtil;
import com.ruoyi.books.domain.Books;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.shoppingCart.mapper.ShoppingCartMapper;
import com.ruoyi.shoppingCart.domain.ShoppingCart;
import com.ruoyi.shoppingCart.service.IShoppingCartService;

import static com.ruoyi.common.utils.SecurityUtils.getUserId;

/**
 * 购物车信息Service业务层处理
 * 
 * @author 智慧的小国
 * @date 2024-06-12
 */
@Service
public class ShoppingCartServiceImpl implements IShoppingCartService 
{
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    SnowFlakeUtil snowFlakeUtil = new SnowFlakeUtil();

    Books books = new Books();

    /**
     * 查询购物车信息
     * 
     * @param scId 购物车信息主键
     * @return 购物车信息
     */
    @Override
    public ShoppingCart selectShoppingCartByScId(String scId)
    {
        return shoppingCartMapper.selectShoppingCartByScId(scId);
    }

    /**
     * 查询购物车信息列表
     * 
     * @param shoppingCart 购物车信息
     * @return 购物车信息
     */
    @Override
    public List<ShoppingCart> selectShoppingCartList(ShoppingCart shoppingCart)
    {
        return shoppingCartMapper.selectShoppingCartList(shoppingCart);
    }

    /**
     * 新增购物车信息
     * 
     * @param shoppingCart 购物车信息
     * @return 结果
     */
    @Override
    public int insertShoppingCart(ShoppingCart shoppingCart)
    {
        shoppingCart.setScId(String.valueOf(snowFlakeUtil.nextId()));
        shoppingCart.setUserId(getUserId());
        shoppingCart.setCreateTime(DateUtils.getNowDate());
        return shoppingCartMapper.insertShoppingCart(shoppingCart);
    }

    /**
     * 修改购物车信息
     * 
     * @param shoppingCart 购物车信息
     * @return 结果
     */
    @Override
    public int updateShoppingCart(ShoppingCart shoppingCart)
    {
        return shoppingCartMapper.updateShoppingCart(shoppingCart);
    }

    /**
     * 批量删除购物车信息
     * 
     * @param scIds 需要删除的购物车信息主键
     * @return 结果
     */
    @Override
    public int deleteShoppingCartByScIds(String[] scIds)
    {
        return shoppingCartMapper.deleteShoppingCartByScIds(scIds);
    }

    /**
     * 删除购物车信息信息
     * 
     * @param scId 购物车信息主键
     * @return 结果
     */
    @Override
    public int deleteShoppingCartByScId(String scId)
    {
        return shoppingCartMapper.deleteShoppingCartByScId(scId);
    }
}
