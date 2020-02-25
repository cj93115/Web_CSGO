package com.Web_CSGO.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author lianglifeng
 * @since 2020-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oc_Informations")
public class OcInformationsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "InformationId", type = IdType.AUTO)
    private Integer InformationId;

    @TableField("UserName")
    private String UserName;

    @TableField("Password")
    private String Password;

    @TableField("AccountType")
    private String AccountType;

    @TableField("Mobile")
    private String Mobile;

    @TableField("Identitys")
    private String Identitys;

    @TableField("Name")
    private String Name;

    @TableField("Gender")
    private String Gender;

    @TableField("Email")
    private String Email;

    @TableField("ProvinceId")
    private Integer ProvinceId;

    @TableField("CityId")
    private Integer CityId;

    @TableField("DistrictID")
    private Integer DistrictID;

    private Double point;

    @TableField("MonthPoint")
    private Double MonthPoint;

    @TableField("PresentExp")
    private Double PresentExp;

    @TableField("State")
    private Integer State;

    @TableField("RecommendName")
    private String RecommendName;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @TableField("SubmitTime")
    private Date SubmitTime;

    @TableField("LastLoginTime")
    private Date LastLoginTime;

    @TableField("LastIp")
    private String LastIp;

    @TableField("CreaterId")
    private Integer CreaterId;

    @TableField("openId")
    private String openId;

    @TableField("LastClearPointTime")
    private Date LastClearPointTime;

    @TableField("LastGivePointTime")
    private Date LastGivePointTime;

    @TableField("PayPasswd")
    private String PayPasswd;

    @TableField("IsActivate")
    private Integer IsActivate;

    /**
     * 会员级别:0普通店铺,1黄金店铺,2钻石店铺
     */
    @TableField("MemberLevel")
    private Integer MemberLevel;

    @TableField("MyInfo")
    private String MyInfo;

    @TableField("Address")
    private String Address;

    @TableField("mySight")
    private String mySight;

    @TableField("QQ")
    private String qq;

    @TableField("Minzu")
    private String Minzu;

    private String pingyin;

    @TableField("ABC")
    private String abc;

    @TableField("head_img")
    private String headImg;

    @TableField("myJob")
    private String myJob;

    private String country;

    @TableField("School")
    private String School;

    private Date birsday;

    @TableField("WeixinMember_openid")
    private String weixinmemberOpenid;

    @TableField("IDCardPic")
    private String IDCardPic;

    @TableField("IDCardPicBack")
    private String IDCardPicBack;

    @TableField("AuthorType")
    private Integer AuthorType;

    @TableField("MyComposition")
    private String MyComposition;

    @TableField("Recommend")
    private Integer Recommend;

    @TableField("ishot")
    private Integer ishot;


}
