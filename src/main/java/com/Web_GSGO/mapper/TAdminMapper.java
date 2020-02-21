package com.Web_GSGO.mapper;


import com.Web_GSGO.entity.AdminUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface TAdminMapper extends BaseMapper<AdminUser> {
    List<AdminUser> getAdminUser();
}
