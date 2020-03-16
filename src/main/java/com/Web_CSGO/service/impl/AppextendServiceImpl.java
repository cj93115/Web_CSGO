package com.Web_CSGO.service.impl;


import com.Web_CSGO.entity.Appextend;
import com.Web_CSGO.mapper.AppextendMapper;
import com.Web_CSGO.service.IAppextendService;
import com.Web_CSGO.common.base.tips.ResultTip;
import com.Web_CSGO.common.enums.CodeEnum;
import com.Web_CSGO.common.util.ToolUtil;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * (Appextend)表服务实现类
 *
 * @author caojie
 * @since 2020-03-15 13:12:47
 */
@Service
public class AppextendServiceImpl extends ServiceImpl<AppextendMapper,Appextend> implements IAppextendService {
    @Override
    public List<Map<String, Object>>  queryAll(Page page, Appextend APPExtend){
    return baseMapper.queryAllList(page,APPExtend);
    }




    @Override
    public ResultTip saveOrUpdateData(Appextend APPExtend) {


        if(ToolUtil.isEmpty(APPExtend)){//判断对象是否为空
            return new ResultTip(CodeEnum.PARAMS_INCOMPLETENESS);
        }
        if (isExist(APPExtend)){//判断唯一字段是否存在
            return new ResultTip(CodeEnum.DEPT_CODE_REPEAT);
        }

        int resultCuont;
        try {
            //没有id则生成uuid做新增,有id则做修改
        if("".equals(APPExtend.getExtendId()) || APPExtend.getExtendId()==null){
            String uuid = UUID.randomUUID().toString();
            APPExtend.setExtendId(uuid);
            resultCuont=baseMapper.insert(APPExtend);
        }else {
            resultCuont=baseMapper.updateById(APPExtend);
        }

            //resultCuont(成功事件)是否大于0,大于则成功,小于则失败
            if (resultCuont>0){
                return new ResultTip(CodeEnum.SUCCESS);
            }else {
                return new ResultTip(CodeEnum.OPERATION_FAILD);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultTip(e.getMessage());
        }
    }

    /**
     * 判断唯一字段是否存在
     * @param APPExtend
     * @return
     */
    public boolean isExist(Appextend APPExtend) {
        Map<String,Object> map =new HashMap<>();
        //填入唯一字段
        map.put("Extend_Name",APPExtend.getExtendName());
        List<Appextend> list=baseMapper.selectByMap(map);

        if(list.size()>0){
            if (list.get(0).getExtendId().equals(APPExtend.getExtendId())){//若有同名是它本身返回false
                return false;
            }
            return true;
        }
        return false;
    }

}