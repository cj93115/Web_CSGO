package com.Web_CSGO.mapper;


import com.Web_CSGO.entity.Room;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface TRoomMapper extends BaseMapper<Room> {
    List<Room> getRoomList(Page page,@Param("params") Room room);

}
