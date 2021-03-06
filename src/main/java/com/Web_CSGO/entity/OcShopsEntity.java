package com.Web_CSGO.entity;


import lombok.Data;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;




/**
 * <p>
 * 
 * </p>
 *
 * @author lianglifeng
 * @since 2020-02-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oc_Shops")
public class OcShopsEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "shopId",type = IdType.NONE)
    private Integer shopId;

    @TableField("ShopTypeId")
    private Integer ShopTypeId;

    @TableField("ShopName")
    private String ShopName;

    @TableField("Discount")
    private Double Discount;

    @TableField("ShopCode")
    private String ShopCode;

    @TableField("serviceCharge")
    private Double serviceCharge;

    @TableField("InformationId")
    private Integer InformationId;

    /**
     * 是否专卖
     */
    private Boolean isprivate;

    @TableField("ShopImg")
    private String ShopImg;

    @TableField("Shoplogo")
    private String Shoplogo;

    @TableField("ShopJoinTime")
    private Date ShopJoinTime;

    @TableField("ShopDescr")
    private String ShopDescr;

    @TableField("ShopTel")
    private String ShopTel;

    @TableField("ShopWorkTime")
    private String ShopWorkTime;


}
