package com.Web_CSGO.controller.room;

import com.Web_CSGO.common.HttpCode;
import com.Web_CSGO.common.base.BaseController;
import com.Web_CSGO.entity.OcShopsEntity;
import com.Web_CSGO.entity.Room;
import com.Web_CSGO.service.IRoomService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *@ClassName: RoomController
 *@Description: TODO
 *Author: cdl
 *@Date: 2020/2/27 13:58
 **/
@Slf4j
@RestController
@RequestMapping("/RoomController")
public class RoomController  extends BaseController {

    @Autowired
    private IRoomService roomService;

    @GetMapping("getRoom")
    public ModelAndView getRoomPage() {
        return new ModelAndView("main/getRoomList");
    }


    @ResponseBody
    @PostMapping("getRoomList")
    public Object getRoomList(HttpServletRequest request, Integer page, Integer rows, Room room) {
        /***********分页部分*************/
        JSONObject object = new JSONObject();
        object.put("current", page);
        object.put("size", rows);
        Integer current = (Integer) setPage(object).get("current");
        if (null == current) {
            current = 0;
        }
        //每页的大小
        Integer size = (Integer) setPage(object).get("size");
        if (null == size) {
            size = 10;
        }
        Page<Object> objectPage = new Page<>(current, size);
        /***********分页部分*************/
        List<Room> roomList = roomService.getRoomList(objectPage, room);
        JSONObject returnJson = new JSONObject();
        returnJson.put("rows", roomList);//每页条数
        returnJson.put("total", objectPage.getTotal());//分页总条数
        return returnJson;
    }

    @PostMapping("addOrUpRoom")
    public Object addOrUpRoom(Room room) {
        JSONObject returnJson = new JSONObject();
        QueryWrapper<Room> roomQuery = new QueryWrapper<>();
        roomQuery.eq("room_name", room.getRoom_name());
        List<Map<String, Object>> list = roomService.listMaps(roomQuery);
        if (list.size() > 0&&!room.getId().equals(list.get(0).get("id"))) {
            return setSuccessJSONObject(HttpCode.BAD_REQUEST, "", "保存失败,房间名存在!");
        }
        if("".equals(room.getId())){
            room.setUp_time(new Date());
        }
        try {
            boolean addOrUp = roomService.saveOrUpdate(room);
            if (addOrUp){
                returnJson = setSuccessJSONObject(HttpCode.SUCCESS, "", "保存成功!");
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return setSuccessJSONObject(HttpCode.BAD_REQUEST, "","保存失败!");
        }
        return returnJson;
    }

    @PostMapping("delRoom")
    public Object delRoom(String id){
        JSONObject returnJson = new JSONObject();
        try {
            boolean remove = roomService.removeById(id);
            if (remove){
                returnJson = setSuccessJSONObject(HttpCode.SUCCESS, "", "删除成功!");
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return setSuccessJSONObject(HttpCode.BAD_REQUEST, "","删除失败!");
        }
        return returnJson;
    }
    @PostMapping("editGetRoom")
    public Object editGetRoom(String id){
        Room room = roomService.getById(id);
        return JSON.toJSON(room);
    }
}
