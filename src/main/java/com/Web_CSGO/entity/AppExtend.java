package com.Web_CSGO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.beans.Transient;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("APPExtend")
public class AppExtend implements Serializable {
      @TableId(value = "Extend_ID",type = IdType.UUID)
      private String extend_ID;
      @TableField("Extend_Name")
      private String extend_Name;
      @TableField("Extend_Order")
      private String extend_Order;
      @TableField("Kind_ID")
      private String Kind_ID;
      @TableField("Extend_Begin")
      private String extend_Begin;
      @TableField("Extend_End")
      private String extend_End;
      @TableField(exist = false)
      private AppKind appKind;


}
