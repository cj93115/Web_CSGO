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
 * (Appextend)实体类
 *
 * @author caojie
 * @since 2020-03-15 13:12:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("APPExtend")
public class Appextend implements Serializable {
    private static final long serialVersionUID = 536824996492254458L;
        @TableId(value="Extend_ID",type = IdType.NONE)
        private String extendId;
        @TableField("Extend_Name")
        private String extendName;
        @TableField("Extend_Order")
        private Integer extendOrder;
        @TableField("Kind_ID")
        private String kindId;
        @TableField("Extend_Begin")
        private String extendBegin;
        @TableField("Extend_End")
        private String extendEnd;



}