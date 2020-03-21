package com.Web_CSGO.entity;


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
 * @since 2020-03-15 16:15:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("APPProduct")
public class Appproduct implements Serializable {
    private static final long serialVersionUID = -88001690933953157L;
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
        private Object productIspass;
        @TableField("Product_Time")
        private String productTime;
        @TableField("Product_Brand")
        private String productBrand;
        @TableField("Product_Market")
        private Double productMarket;
        @TableField("Product_Member")
        private Double productMember;
        @TableField("Product_VIP")
        private Double productVip;
        @TableField("Product_Stock")
        private Double productStock;
        @TableField("Product_Sale")
        private Double productSale;
        @TableField("Product_Point")
        private Double productPoint;
    /**
    * 钻石店铺
    */    @TableField("Product_Hight")
        private Double productHight;
    /**
    * 黄金店铺
    */    @TableField("Product_Middel")
        private Double productMiddel;
    /**
    * 普通店铺
    */    @TableField("Product_Lower")
        private Double productLower;
    /**
    * 是否私有
    */    @TableField("Product_isprivate")
        private Object productIsprivate;
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
        @TableField("Product_SendByPost")
        private Integer productSendbypost;
        @TableField("Product_One")
        private Double productOne;
        @TableField("Product_Two")
        private Double productTwo;
        @TableField("Product_Three")
        private Double productThree;



}