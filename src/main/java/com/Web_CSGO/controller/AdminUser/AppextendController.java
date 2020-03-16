package com.Web_CSGO.controller.AdminUser;

import com.Web_CSGO.entity.Appextend;
import com.Web_CSGO.service.IAppextendService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import com.Web_CSGO.common.util.PageUtil;
import com.Web_CSGO.common.base.tips.ResultTip;
import com.Web_CSGO.common.enums.CodeEnum;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Appextend)表控制层
 *
 * @author caojie
 * @since 2020-03-15 13:19:42
 */
@RestController
@RequestMapping("appextend")
public class AppextendController {
    /**
     * 服务对象
     */
    @Resource
    private IAppextendService appextendService;


   //返回该页面的视图
      @GetMapping("getAppextend")
    public ModelAndView getAdminUserPage(){
        return new ModelAndView("main/getAppextendList");
    }
    /**
     * 通过主键查询单条数据
     *
     * @param  extendId 主键
     * @return 单条数据
     */    
     @PostMapping("selectOne")
    public Appextend selectOne(String extendId) {
        return appextendService.getById(extendId);
    }
    
        /**
     * 通过条件分页查询
     * @param APPExtend
     * @return
     */
    @PostMapping("queryAll")
    @ResponseBody
    public ResultTip queryAll(Appextend APPExtend) {
     Page<Map<String, Object>> page= PageUtil.defaultPage();
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> list =appextendService.queryAll(page,APPExtend);
        page.setRecords(list);
        return new ResultTip(CodeEnum.SUCCESS, page);
    }
    
    
        /**
     * 新增或修改
     * @param  APPExtend
     * @return
     */
    @PostMapping("/saveOrUpdateData")
    @ResponseBody
    public ResultTip saveOrUpdateData(Appextend APPExtend){ return appextendService.saveOrUpdateData(APPExtend);
    }
    

    /**
     * 删除功能
      * @param  extendId 主键
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public ResultTip delete(String extendId){
        try {
           if(appextendService.removeById(extendId)){
              return new ResultTip(CodeEnum.DELETE_SUCCESS);
          }
              return new ResultTip(CodeEnum.OPERATION_FAILD);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultTip(e.getMessage());
        }
    }


}