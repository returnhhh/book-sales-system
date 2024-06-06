package com.ruoyi.web.domain;

import lombok.Data;

/**
 * 图书实体类
 */

@Data
public class Book {
    /**
     * 书籍id
     */
    private Integer bookId;

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
     * 商品介绍
     */
    private String bookIntroduce;

    /**
     * 出版时间
     */

    private Data bookPresstime;

    /**
     * 库存
     */
    private Integer book_num;
}
