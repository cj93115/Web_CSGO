package com.Web_CSGO.entity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_test")
public class UserTest  implements Serializable  {

    //private static final long serialVersionUID= 1L;

    @TableId(value = "id",type = IdType.UUID)
    String Id;

    @TableId("name")
    String Name;



}
