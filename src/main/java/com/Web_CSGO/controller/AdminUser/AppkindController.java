package com.Web_CSGO.controller.AdminUser;

import com.Web_CSGO.common.HttpCode;
import com.Web_CSGO.common.base.BaseController;
import com.Web_CSGO.common.enums.CodeEnum;
import com.Web_CSGO.entity.Appkind;
import com.Web_CSGO.service.IAppkindService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import com.Web_CSGO.common.util.PageUtil;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Appkind)表控制层
 *
 * @author makejava
 * @since 2020-03-13 16:19:00
 */
@RestController
@RequestMapping("appkind")
public class AppkindController extends BaseController{
    /**
     * 服务对象
     */
    @Resource
    private IAppkindService appkindService;

    @GetMapping("getAppKind")
    public ModelAndView getAdminUserPage(){
        return new ModelAndView("main/getAppKindList");
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Appkind selectOne(String id) {
        return appkindService.getById(id);
    }
    
    @PostMapping("queryAll")
    @ResponseBody
    public JSONObject queryAll(Appkind APPKind) {
     Page<Appkind> page= PageUtil.defaultPage();
        Map<String, Object> map = new HashMap<String, Object>();
        List<Appkind> list =appkindService.queryAll(page,APPKind);
        page.setRecords(list);
        map.put("total" ,page.getTotal());
        map.put("rows",list);

        return new JSONObject(map);
    }
    
        //添加
    @RequestMapping("/save")
    @ResponseBody
    public JSONObject save(Appkind APPKind){
            return appkindService.saveData(APPKind);
    }
    //修改
    @RequestMapping("/update")
    @ResponseBody
    public JSONObject update(@ModelAttribute("editAppkind")Appkind APPKind){
        try {
            appkindService.updateData(APPKind);
            appkindService.updateById(APPKind);
            return setSuccessJSONObject(CodeEnum.SUCCESS.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return setSuccessJSONObject(CodeEnum.SAVE_FAILD.getMsg());
        }
    }
    /**
     * 删除功能,前台要求返回{success:true/false,msg:xxx}
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JSONObject delete(Long id){
        try {
           appkindService.removeById(id);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}