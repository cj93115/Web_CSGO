package com.Web_CSGO.controller.AdminUser;

import com.Web_CSGO.common.util.ToolUtil;
import com.Web_CSGO.entity.Appkind;
import com.Web_CSGO.service.IAppkindService;
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
 * (Appkind)表控制层
 *
 * @author makejava
 * @since 2020-03-15 12:25:57
 */
@RestController
@RequestMapping("appkind")
public class AppkindController {
    /**
     * 服务对象
     */
    @Resource
    private IAppkindService appkindService;


   //返回该页面的视图
      @GetMapping("getAppkind")
    public ModelAndView getAdminUserPage(){
        return new ModelAndView("main/getAppkindList");
    }
    /**
     * 通过主键查询单条数据
     *
     * @param  kindId 主键
     * @return 单条数据
     */    
     @PostMapping("selectOne")
    public Appkind selectOne(String kindId) {
        return appkindService.getById(kindId);
    }
    
        /**
     * 通过条件分页查询
     * @param APPKind
     * @return
     */
    @PostMapping("queryAll")
    @ResponseBody
    public ResultTip queryAll(Appkind APPKind) {
        if (ToolUtil.isEmpty(APPKind)){return null; }
     Page<Appkind> page= PageUtil.defaultPage();
        Map<String, Object> map = new HashMap<String, Object>();
        List<Appkind> list =appkindService.queryAll(page,APPKind);
        page.setRecords(list);
        return new ResultTip(CodeEnum.SUCCESS, page);
    }
    
    
        /**
     * 新增或修改
     * @param APPKind
     * @return
     */
    @PostMapping("/saveOrUpdateData")
    @ResponseBody
    public ResultTip saveOrUpdateData(Appkind APPKind){ return appkindService.saveOrUpdateData(APPKind);
    }
    

    /**
     * 删除功能
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public ResultTip delete(String kindId){
        if (kindId==null || "".equals(kindId)){
            return new ResultTip(CodeEnum.PARAMS_INCOMPLETENESS);
        }
        try {
           if(appkindService.removeById(kindId)){
              return new ResultTip(CodeEnum.DELETE_SUCCESS);
          }
              return new ResultTip(CodeEnum.OPERATION_FAILD);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultTip(e.getMessage());
        }
    }
    @PostMapping("/getKind")
    @ResponseBody
    public List<Appkind> getKind(){
           return appkindService.list();
    }

}