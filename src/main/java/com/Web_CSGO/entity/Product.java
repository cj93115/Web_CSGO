package com.Web_CSGO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String Product_ID;
    @TableField("Product_Name")
    private String Product_Name;
    @TableField("Product_Pictrure")
    private String Product_Pictrure;
    @TableField("Product_Time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Object Product_Time;
    @TableField("Product_Sale")
    private float Product_Sale;
    @TableField("Product_PicSmall")
    private String Product_PicSmall;
    @TableField("Extend_ID")
    private String Extend_ID;
}
