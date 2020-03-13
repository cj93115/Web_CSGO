package com.Web_CSGO.service.impl;

import com.Web_CSGO.common.enums.CodeEnum;
import com.Web_CSGO.common.util.ToolUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.Web_CSGO.entity.Appkind;
import com.Web_CSGO.mapper.AppkindMapper;
import com.Web_CSGO.service.IAppkindService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Appkind)表服务实现类
 *
 * @author makejava
 * @since 2020-03-13 16:19:00
 */
@Service
public class AppkindServiceImpl extends ServiceImpl<AppkindMapper,Appkind> implements IAppkindService {
    @Override
    public List<Appkind>  queryAll(Page page, Appkind APPKind){
    return baseMapper.queryAllList(page,APPKind);
    }

    @Override
    public JSONObject saveData(Appkind APPKind) {
        JSONObject json = new JSONObject();

        if(ToolUtil.isEmpty(APPKind)){
            json.put("msg", CodeEnum.PARAMS_INCOMPLETENESS.getMsg());
            return json;
        }
        if (isExist(APPKind)){
            json.put("msg", CodeEnum.DEPT_CODE_REPEAT.getMsg());
            return json;
        }
        try {
            int resultCont=baseMapper.insert(APPKind);
            if (resultCont>0){
                json.put("msg", CodeEnum.SUCCESS.getMsg());
            }else {
                json.put("msg", CodeEnum.SAVE_FAILD.getMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
           json.put("msg",e.getMessage());
        }finally {
            return json;
        }
    }

    @Override
    public JSONObject updateData(Appkind APPKind) {
        JSONObject json = new JSONObject();

        if(ToolUtil.isEmpty(APPKind)){
            json.put("msg", CodeEnum.PARAMS_INCOMPLETENESS.getMsg());
            return json;
        }
        if (isExist(APPKind)){
            json.put("msg", CodeEnum.DEPT_CODE_REPEAT.getMsg());
            return json;
        }
        try {
            int resultCont=baseMapper.updateById(APPKind);
            if (resultCont>0){
                json.put("msg", CodeEnum.SUCCESS.getMsg());
            }else {
                json.put("msg", CodeEnum.UPDATE_FAILD.getMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg",e.getMessage());
        }finally {
            return json;
        }

    }

    public boolean isExist(Appkind APPKind) {
        Map<String,Object> map =new HashMap<>();
        map.put("Kind_Name",APPKind.getKindName());
        List<Appkind> list=baseMapper.selectByMap(map);
        if(list.size()>0){
            return true;
        }
        return false;
    }


}