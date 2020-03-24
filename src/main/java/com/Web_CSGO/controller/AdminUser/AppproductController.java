package com.Web_CSGO.controller.AdminUser;

import com.Web_CSGO.common.HttpCode;
import com.Web_CSGO.common.base.BaseController;
import com.Web_CSGO.common.util.ToolUtil;
import com.Web_CSGO.entity.Appextend;
import com.Web_CSGO.entity.Appproduct;
import com.Web_CSGO.service.IAppproductService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.http.HttpRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.Web_CSGO.common.util.PageUtil;
import com.Web_CSGO.common.base.tips.ResultTip;
import com.Web_CSGO.common.enums.CodeEnum;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Appproduct)表控制层
 *
 * @author caojie
 * @since 2020-03-15 16:15:07
 */
@RestController
@RequestMapping("appproduct")
public class AppproductController extends BaseController{
    /**
     * 服务对象
     */
    @Resource
    private IAppproductService appproductService;


   //返回物品管理页面
      @GetMapping("getAppproduct")
    public ModelAndView getAdminUserPage(String  extendId,String extendPic,String extendName){
          ModelMap model=new ModelMap();
          model.addAttribute("extendId",extendId);
          model.addAttribute("extendPic","/csgo_img/"+extendPic);
          model.addAttribute("extendName",extendName);
          return new ModelAndView("main/getAppproductList",model);
    }
    //返回商城物品页面
      @GetMapping("getStoreProduct")
    public ModelAndView getStoreProduct(String  extendId,String extendPic,String extendName){
          ModelMap model=new ModelMap();
          model.addAttribute("extendId",extendId);
          model.addAttribute("extendPic","/csgo_img/"+extendPic);
          model.addAttribute("extendName",extendName);
          return new ModelAndView("main/getStoreProductList",model);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param  productId 主键
     * @return 单条数据
     */    
     @PostMapping("selectOne")
    public Appproduct selectOne(String productId) {
        return appproductService.getById(productId);
    }
    
        /**
     * 通过条件分页查询
     * @param APPProduct
     * @return
     */
    @PostMapping("queryAll")
    @ResponseBody
    public ResultTip queryAll(Appproduct APPProduct) {
        if (ToolUtil.isEmpty(APPProduct)){return null; }
     Page<Appproduct> page= PageUtil.defaultPage();
        Map<String, Object> map = new HashMap<String, Object>();
        List<Appproduct> list =appproductService.queryAll(page,APPProduct);
        page.setRecords(list);
        return new ResultTip(CodeEnum.SUCCESS, page);
    }
    
    
        /**
     * 新增或修改
     * @param  APPProduct
     * @return
     */

    @PostMapping("/saveOrUpdateData")
    @ResponseBody
    public ResultTip saveOrUpdateData(Appproduct APPProduct){
        return appproductService.saveOrUpdateData(APPProduct);
    }
    

    /**
     * 删除功能
      * @param  productId 主键
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultTip delete(String productId){
        if (productId==null || "".equals(productId)){
            return new ResultTip(CodeEnum.PARAMS_INCOMPLETENESS);
        }
        try {
           if(appproductService.removeById(productId)){
              return new ResultTip(CodeEnum.DELETE_SUCCESS);
          }
              return new ResultTip(CodeEnum.OPERATION_FAILD);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultTip(e.getMessage());
        }
    }
    /**
     * 图片上传功能
      * @param
     * @return
     */
    @PostMapping("imageUpload")
    @ResponseBody
    public Object filesUpload(@RequestParam(value="file") MultipartFile file) {
        String filename= ToolUtil.filesUpload(file);
        if(filename!=null){
            return new ResultTip(CodeEnum.SUCCESS,filename);
        }
        return new ResultTip(CodeEnum.OPERATION_FAILD);
    }

}