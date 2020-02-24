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
      private String extendId;
      @TableField("Extend_Name")
      private String extendName;
      @TableField("Extend_Order")
      private String extendOrder;
      @TableField("Kind_Id")
      private String kindId;
      @TableField("Extend_Begin")
      private String extendBegin;
      @TableField("Extend_End")
      private String extendEnd;

      private AppKind appKind;


}
