package com.Web_CSGO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("APPProduct")
public class Product implements Serializable {
    @TableId(value = "Product_ID",type = IdType.UUID)
    private String productId;
    @TableField("Product_Name")
    private String productName;
    @TableField("Product_Pictrure")
    private String productPictrure;
    @TableField("Product_Time")
    private String productTime;
    @TableField("Product_Sale")
    private float productSale;
    @TableField("Product_PicSmall")
    private String productPicSmall;
}
