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
@TableName("APPKind")
public class AppKind implements Serializable {
    @TableId(value = "Kind_ID",type = IdType.UUID)
    private String kindId;
    @TableField("Kind_Name")
    private String kindName;
    @TableField("Kind_Order")
    private String kindOrder;
}
