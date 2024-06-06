package com.ruoyi.domain;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.uuid.IdUtils;
import lombok.Data;

import java.util.Date;

/**
 * 图书数据对象 books
 *
 * @author 智慧的小国
 * @date 2024-06-06
 */

@Data
public class Book {
    /**
     * 书籍id
     */
    private String bookId;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 书籍名称
     */
    private String bookName;

    /**
     * 作者
     */
    private String author;

    /**
     * 出版社
     */
    private String press;

    /**
     * 图书类型
     */
    private String bookType;

    /**
     * 图书图片
     */
    private String bookImg;

    /**
     * 价格
     */
    private double bookPrice;

    /**
     * 书籍简介
     */
    private String bookIntroduce;

    /**
     * 出版时间
     */

    private Date bookPressTime;

    /**
     * 库存
     */
    private Integer bookNum;

    /**
     * 加入购物车时间
     */
    private Date createTime;

    public void init(SysUser user){
        this.setBookId(IdUtils.fastUUID());
        this.setCreateBy(user.getCreateBy());
        this.setUpdateBy(user.getUpdateBy());
        this.setUpdateDate(new Date());
        this.setCreateTime(new Date());
    }
}
