package com.ruoyi.books.domain;

import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 图书商城信息对象 books
 * 
 * @author 智慧的小国
 * @date 2024-06-07
 */
public class Books extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图书id */
    private String bookId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    /** 书籍名称 */
    @Excel(name = "书籍名称")
    private String bookName;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 出版社 */
    @Excel(name = "出版社")
    private String press;

    /** 图书类型 */
    @Excel(name = "图书类型")
    private String bookType;

    /** 图片 */
    @Excel(name = "图片")
    private String bookImg;

    /** 价格 */
    @Excel(name = "价格")
    private Double bookPrice;

    /** 书籍简介 */
    @Excel(name = "书籍简介")
    private String bookInventory;

    /** 出版时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出版时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date bookPressTime;

    /** 库存 */
    @Excel(name = "库存")
    private Long bookNum;

    /** 排序 */
    @Excel(name = "排序")
    private Integer bookNumber;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setCreateDate(LocalDateTime createDate)
    {
        this.createDate = createDate;
    }

    public LocalDateTime getCreateDate()
    {
        return createDate;
    }
    public void setUpdateDate(LocalDateTime updateDate)
    {
        this.updateDate = updateDate;
    }

    public LocalDateTime getUpdateDate()
    {
        return updateDate;
    }
    public void setBookName(String bookName) 
    {
        this.bookName = bookName;
    }

    public String getBookName() 
    {
        return bookName;
    }
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setPress(String press) 
    {
        this.press = press;
    }

    public String getPress() 
    {
        return press;
    }
    public void setBookType(String bookType) 
    {
        this.bookType = bookType;
    }

    public String getBookType() 
    {
        return bookType;
    }
    public void setBookImg(String bookImg) 
    {
        this.bookImg = bookImg;
    }

    public String getBookImg() 
    {
        return bookImg;
    }
    public void setBookPrice(Double bookPrice) 
    {
        this.bookPrice = bookPrice;
    }

    public Double getBookPrice() 
    {
        return bookPrice;
    }
    public void setBookInventory(String bookInventory) 
    {
        this.bookInventory = bookInventory;
    }

    public String getBookInventory() 
    {
        return bookInventory;
    }
    public void setBookPressTime(Date bookPressTime) 
    {
        this.bookPressTime = bookPressTime;
    }

    public Date getBookPressTime() 
    {
        return bookPressTime;
    }
    public void setBookNum(Long bookNum) 
    {
        this.bookNum = bookNum;
    }

    public Long getBookNum() 
    {
        return bookNum;
    }

    public Integer getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(Integer bookNumber) {
        this.bookNumber = bookNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bookId", getBookId())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("bookName", getBookName())
            .append("author", getAuthor())
            .append("press", getPress())
            .append("bookType", getBookType())
            .append("bookImg", getBookImg())
            .append("bookPrice", getBookPrice())
            .append("bookInventory", getBookInventory())
            .append("bookPressTime", getBookPressTime())
            .append("bookNum", getBookNum())
            .append("createTime", getCreateTime())
            .toString();
    }
}
