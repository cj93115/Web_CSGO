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
 * (UserTest)实体类
 *
 * @author makejava
 * @since 2020-03-13 09:41:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_test")
public class UserTest implements Serializable {
    private static final long serialVersionUID = -97809064642234897L;


        @TableId(value="id",type = IdType.NONE)
        private Integer id;


        @TableField("name")
        private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}