package com.Web_CSGO.service;

import com.Web_CSGO.entity.Room;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * (Room)表服务接口
 *
 * @author makejava
 * @since 2020-02-26 17:33:23
 */
public interface IRoomService extends IService<Room> {
    List<Room> getRoomList(Page page, Room room);
}