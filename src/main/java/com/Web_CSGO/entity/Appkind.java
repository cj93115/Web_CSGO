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
 * (Appkind)实体类
 *
 * @author makejava
 * @since 2020-03-13 16:19:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("APPKind")
public class Appkind implements Serializable {
    private static final long serialVersionUID = 842857606562057840L;


        @TableId(value="Kind_ID",type = IdType.NONE)
        private String kindId;
        @TableField("Kind_Name")
        private String kindName;
        @TableField("Kind_Order")
        private Integer kindOrder;



}