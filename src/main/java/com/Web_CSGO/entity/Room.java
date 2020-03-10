package com.Web_CSGO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (Room)实体类
 *
 * @author makejava
 * @since 2020-02-26 17:33:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("room")
public class Room implements Serializable {
    private static final long serialVersionUID = 695260190473588341L;
    @TableId(value = "id",type = IdType.UUID)
    private String id;

    @TableId(value = "room_name")
    private String room_name;

    @TableId(value = "open_box")
    private String open_box;

    @TableId(value = "account_balance")
    private Double account_balance;

    @TableId(value = "start_time")
    private Object start_time;

    @TableId(value = "end_time")
    private Object end_time;

    @TableId(value = "c_time")
    private Object c_time;

    @TableId(value = "up_time")
    private Object up_time;

    @TableId(value = "number")
    private Object number;

}