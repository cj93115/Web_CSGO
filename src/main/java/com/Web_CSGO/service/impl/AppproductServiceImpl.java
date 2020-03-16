package com.Web_CSGO.service.impl;


import com.Web_CSGO.common.util.DateUtil;
import com.Web_CSGO.entity.Appproduct;
import com.Web_CSGO.mapper.AppproductMapper;
import com.Web_CSGO.service.IAppproductService;
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
 * (Appproduct)表服务实现类
 *
 * @author caojie
 * @since 2020-03-15 16:15:07
 */
@Service
public class AppproductServiceImpl extends ServiceImpl<AppproductMapper,Appproduct> implements IAppproductService {
    @Override
    public List<Appproduct>  queryAll(Page page, Appproduct APPProduct){
    return baseMapper.queryAllList(page,APPProduct);
    }




    @Override
    public ResultTip saveOrUpdateData(Appproduct APPProduct) {


        if(ToolUtil.isEmpty(APPProduct)){//判断对象是否为空
            return new ResultTip(CodeEnum.PARAMS_INCOMPLETENESS);
        }
        if (isExist(APPProduct)){//判断唯一字段是否存在
            return new ResultTip(CodeEnum.DEPT_CODE_REPEAT);
        }

        int resultCuont;
        try {
            //没有id则生成uuid做新增,有id则做修改
        if("".equals(APPProduct.getProductId()) || APPProduct.getProductId()==null){
            String uuid = UUID.randomUUID().toString();
            APPProduct.setProductId(uuid);
            APPProduct.setProductTime(DateUtil.getTime());
            resultCuont=baseMapper.insert(APPProduct);
        }else {
            resultCuont=baseMapper.updateById(APPProduct);
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
     * @param APPProduct
     * @return
     */
    public boolean isExist(Appproduct APPProduct) {
        Map<String,Object> map =new HashMap<>();
        //填入唯一字段
        map.put("Product_Name",APPProduct.getProductName());
        map.put("Product_Brand",APPProduct.getProductBrand());
        List<Appproduct> list=baseMapper.selectByMap(map);

        if(list.size()>0){
            if (list.get(0).getProductId().equals(APPProduct.getProductId())){//若有同名是它本身返回false
                return false;
            }
            return true;
        }
        return false;
    }

}