package com.ruoyi.shoppingCart.service;

import java.util.List;
import com.ruoyi.shoppingCart.domain.ShoppingCart;

/**
 * 购物车信息Service接口
 * 
 * @author 智慧的小国
 * @date 2024-06-12
 */
public interface IShoppingCartService 
{
    /**
     * 查询购物车信息
     * 
     * @param scId 购物车信息主键
     * @return 购物车信息
     */
    public ShoppingCart selectShoppingCartByScId(String scId);

    /**
     * 查询购物车信息列表
     * 
     * @param shoppingCart 购物车信息
     * @return 购物车信息集合
     */
    public List<ShoppingCart> selectShoppingCartList(ShoppingCart shoppingCart);

    /**
     * 新增购物车信息
     * 
     * @param shoppingCart 购物车信息
     * @return 结果
     */
    public int insertShoppingCart(ShoppingCart shoppingCart);

    /**
     * 修改购物车信息
     * 
     * @param shoppingCart 购物车信息
     * @return 结果
     */
    public int updateShoppingCart(ShoppingCart shoppingCart);

    /**
     * 批量删除购物车信息
     * 
     * @param scIds 需要删除的购物车信息主键集合
     * @return 结果
     */
    public int deleteShoppingCartByScIds(String[] scIds);

    /**
     * 删除购物车信息信息
     * 
     * @param scId 购物车信息主键
     * @return 结果
     */
    public int deleteShoppingCartByScId(String scId);
}
