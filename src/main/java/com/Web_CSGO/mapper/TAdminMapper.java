package com.Web_CSGO.mapper;


import com.Web_CSGO.entity.AdminUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface TAdminMapper extends BaseMapper<AdminUser> {
    List<AdminUser> getAdminUser();
}
