package com.ruoyi.domain.bo.addBo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 图书业务对象 books
 *
 * @author 智慧的小国
 * @date 2024-06-06
 */
@Data
public class AddBookBo {
    /**
     * 书籍名称
     */
    @NotBlank(message = "书名不能为空")
    private String bookName;

    /**
     * 作者
     */
    @NotBlank(message = "作者不能为空")
    private String author;

    /**
     * 出版社
     */
    @NotBlank(message = "出版社不能为空")
    private String press;

    /**
     * 图书类型
     */
    @NotBlank(message = "图书类型不能为空")
    private String bookType;

    /**
     * 图书图片
     */
    @NotBlank(message = "图书图片不能为空")
    private String bookImg;

    /**
     * 价格
     */
    @NotNull(message = "价格不能为空")
    private double bookPrice;

    /**
     * 书籍简介
     */
    private String bookIntroduce;

    /**
     * 出版时间
     */
    @NotNull(message = "出版时间不能为空")
    private Date bookPressTime;

    /**
     * 库存
     */
    @NotNull(message = "库存不能为空")
    private Integer bookNum;
}
