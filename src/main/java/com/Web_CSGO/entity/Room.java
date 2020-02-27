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
    private String roomName;

    @TableId(value = "open_box")
    private String openBox;

    @TableId(value = "account_balance")
    private Double accountBalance;

    @TableId(value = "start_time")
    private Object startTime;

    @TableId(value = "end_time")
    private Object endTime;

    @TableId(value = "c_time")
    private Object cTime;

    @TableId(value = "up_time")
    private Object upTime;

}