package com.ruoyi.orderManagement.domain;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单管理对象 order
 * 
 * @author 智慧的小国
 * @date 2024-06-11
 */
public class Order extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单id */
    private String orderId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 书名 */
    @Excel(name = "书名")
    private String bookName;

    /** 图书id */
    @Excel(name = "图书id")
    private String bookId;

    /** 价格 */
    @Excel(name = "价格")
    private Double price;

    /** 数量 */
    @Excel(name = "数量")
    private Long num;

    /** 总价 */
    @Excel(name = "总价")
    private Double allPrice;

    /** 收货人姓名 */
    @Excel(name = "收货人姓名")
    private String contactName;

    /** 收货人手机号 */
    @Excel(name = "收货人手机号")
    private String phone;

    /** 收货人地址 */
    @Excel(name = "收货人地址")
    private String address;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    /** 订单状态  */
    @Excel(name = "订单状态")
    private String state;


    /** 订单数量统计  */
    @Excel(name = "数量")
    private Long number;

    /** 支付成功时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付成功时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;




    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setBookName(String bookName) 
    {
        this.bookName = bookName;
    }

    public String getBookName() 
    {
        return bookName;
    }
    public void setBookId(String bookId) 
    {
        this.bookId = bookId;
    }

    public String getBookId() 
    {
        return bookId;
    }
    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Double getPrice()
    {
        return price;
    }
    public void setNum(Long num) 
    {
        this.num = num;
    }

    public Long getNum() 
    {
        return num;
    }
    public void setAllPrice(Double allPrice)
    {
        this.allPrice = allPrice;
    }

    public Double getAllPrice()
    {
        return allPrice;
    }
    public void setContactName(String contactName) 
    {
        this.contactName = contactName;
    }

    public String getContactName() 
    {
        return contactName;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("userName", getUserName())
            .append("bookName", getBookName())
            .append("bookId", getBookId())
            .append("price", getPrice())
            .append("num", getNum())
            .append("allPrice", getAllPrice())
            .append("contactName", getContactName())
            .append("phone", getPhone())
            .append("address", getAddress())
            .append("createTime", getCreateDate())
            .append("updateTime", getUpdateDate())
            .append("state",getState())
            .append("number",getNumber())
            .append("time",getTime())
            .toString();
    }
}
