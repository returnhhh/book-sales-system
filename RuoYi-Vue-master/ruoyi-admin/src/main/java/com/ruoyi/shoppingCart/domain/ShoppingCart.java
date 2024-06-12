package com.ruoyi.shoppingCart.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 购物车信息对象 shopping_cart
 * 
 * @author 智慧的小国
 * @date 2024-06-12
 */
public class ShoppingCart extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 购物车id */
    private String scId;

    /** 图书id */
    @Excel(name = "图书id")
    private String bookId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 数量 */
    @Excel(name = "数量")
    private Long num;

    /** 书名 */
    private String bookName;

    /** 图片 */
    private String bookImg;

    /** 价格 */
    private Double price;

    /** 用户名 */
    private String userName;

    public void setScId(String scId) 
    {
        this.scId = scId;
    }

    public String getScId() 
    {
        return scId;
    }
    public void setBookId(String bookId) 
    {
        this.bookId = bookId;
    }

    public String getBookId() 
    {
        return bookId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setNum(Long num) 
    {
        this.num = num;
    }

    public Long getNum() 
    {
        return num;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("scId", getScId())
            .append("bookId", getBookId())
            .append("userId", getUserId())
            .append("num", getNum())
            .append("bookName", getBookName())
            .append("bookImg", getBookImg())
            .append("price", getPrice())
            .append("userName", getUserName())
            .toString();
    }
}
