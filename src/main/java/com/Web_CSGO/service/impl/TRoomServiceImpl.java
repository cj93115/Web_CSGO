package com.Web_CSGO.service.impl;

import com.Web_CSGO.entity.Room;
import com.Web_CSGO.mapper.TRoomMapper;
import com.Web_CSGO.service.IRoomService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@ClassName: TRoomServiceImpl
 *@Description: TODO
 *Author: cdl
 *@Date: 2020/2/27 14:00
 **/
@Service
public class TRoomServiceImpl  extends ServiceImpl<TRoomMapper, Room> implements IRoomService {
    @Override
    public List<Room> getRoomList(Page page, Room room) {
        return this.baseMapper.getRoomList(page,room);
    }
}
