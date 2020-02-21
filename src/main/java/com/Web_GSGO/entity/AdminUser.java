package com.Web_GSGO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 *@ClassName AdminUser
 *@Description TODO
 *Author cdl
 *@Date 2020/2/20 9:20
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("AdminUser")
public class AdminUser implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id",type = IdType.UUID)
    private String User_ID;
    private String usetName;
    private String usetPsd;
    private Date usetLoginTime;
    private String userDuty;
    private String usetTrust;
    private Date usetRegData;
    private String usetMoney;
    private Boolean usetShow;
    private String wxId;
}
