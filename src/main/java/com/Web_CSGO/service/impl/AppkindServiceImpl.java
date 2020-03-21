package com.Web_CSGO.service.impl;


import com.Web_CSGO.entity.Appkind;
import com.Web_CSGO.mapper.AppkindMapper;
import com.Web_CSGO.service.IAppkindService;
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
 * (Appkind)表服务实现类
 *
 * @author makejava
 * @since 2020-03-15 12:23:24
 */
@Service
public class AppkindServiceImpl extends ServiceImpl<AppkindMapper,Appkind> implements IAppkindService {
    @Override
    public List<Appkind>  queryAll(Page page, Appkind APPKind){
    return baseMapper.queryAllList(page,APPKind);
    }




    @Override
    public ResultTip saveOrUpdateData(Appkind APPKind) {


        if(ToolUtil.isEmpty(APPKind)){//判断对象是否为空
            return new ResultTip(CodeEnum.PARAMS_INCOMPLETENESS);
        }
        if (isExist(APPKind)){//判断唯一字段是否存在
            return new ResultTip(CodeEnum.DEPT_CODE_REPEAT);
        }

        int resultCuont;
        try {
            //没有id则生成uuid做新增,有id则做修改
        if("".equals(APPKind.getKindId()) || APPKind.getKindId()==null){
            String uuid = UUID.randomUUID().toString();
            APPKind.setKindId(uuid);
            resultCuont=baseMapper.insert(APPKind);
        }else {
            resultCuont=baseMapper.updateById(APPKind);
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
     * @param APPKind
     * @return
     */
    public boolean isExist(Appkind APPKind) {
        Map<String,Object> map =new HashMap<>();
        //填入唯一字段
        map.put("Kind_Name",APPKind.getKindName());
        List<Appkind> list=baseMapper.selectByMap(map);

        if(list.size()>0){
            if (list.get(0).getKindId().equals(APPKind.getKindId())){//若有同名是它本身返回false
                return false;
            }
            return true;
        }
        return false;
    }

}