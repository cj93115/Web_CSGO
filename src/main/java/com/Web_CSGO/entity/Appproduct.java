package com.Web_CSGO.entity;

import java.util.Date;
import lombok.Data;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (Appproduct)实体类
 *
 * @author caojie
 * @since 2020-03-25 11:17:33
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("APPProduct")
public class Appproduct implements Serializable {
    private static final long serialVersionUID = 487454782546010332L;
        @TableId(value="Product_ID",type = IdType.NONE)
        private String productId;
        @TableField("Product_No")
        private String productNo;
        @TableField("Product_Name")
        private String productName;
        @TableField("Product_Spec")
        private String productSpec;
        @TableField("Product_Com")
        private String productCom;
        @TableField("Product_Explain")
        private String productExplain;
        @TableField("Product_Pictrure")
        private String productPictrure;
        @TableField("Product_IsPass")
        private Boolean productIspass;
        @TableField("Product_Time")
        private String productTime;
        @TableField("Product_Brand")
        private String productBrand;
        @TableField("Product_Market")
        private Object productMarket;
        @TableField("Product_Member")
        private Object productMember;
        @TableField("Product_VIP")
        private Object productVip;
        @TableField("Product_Stock")
        private Object productStock;
        @TableField("Product_Sale")
        private Double productSale;
        @TableField("Product_Point")
        private Object productPoint;
    /**
    * 钻石店铺
    */    @TableField("Product_Hight")
        private Object productHight;
    /**
    * 黄金店铺
    */    @TableField("Product_Middel")
        private Object productMiddel;
    /**
    * 普通店铺
    */    @TableField("Product_Lower")
        private Object productLower;
    /**
    * 是否私有
    */    @TableField("Product_isprivate")
        private Boolean productIsprivate;
        @TableField("Product_PicSmall")
        private String productPicsmall;
        @TableField("Product_Position")
        private String productPosition;
        @TableField("Product_Note")
        private String productNote;
        @TableField("Product_Detail")
        private String productDetail;
        @TableField("Product_Business")
        private String productBusiness;
    /**
    * 推荐或者热门
    */    @TableField("Product_Recommend")
        private Integer productRecommend;
        @TableField("Product_Word")
        private String productWord;
        @TableField("Extend_ID")
        private String extendId;
        @TableField("comment_ID")
        private String commentId;
        @TableField("shopId")
        private Integer shopid;
        @TableField("Product_Count")
        private Integer productCount;
        @TableField("Product_One")
        private Object productOne;
    /**
    * 排序时间
    */    @TableField("Product_Two")
        private Object productTwo;
        @TableField("Product_Three")
        private Object productThree;
        @TableField("Order_Time")
        private String orderTime;



}