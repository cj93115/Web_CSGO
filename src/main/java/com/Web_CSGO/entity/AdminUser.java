package com.Web_CSGO.entity;

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
    private String userName;
    private String User_Psd;
    private Date User_LoginTime;
    private String User_duty;
    private String User_Trust;
    private Date User_RegDate;
    private String User_Money;
    private Boolean User_Show;
    private String wx_id;
}
