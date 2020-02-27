package com.Web_CSGO.controller.AdminUser;

import com.Web_CSGO.common.HttpCode;
import com.Web_CSGO.common.base.BaseController;
import com.Web_CSGO.entity.AppExtend;
import com.Web_CSGO.entity.AppKind;
import com.Web_CSGO.entity.Product;
import com.Web_CSGO.service.IAppExtendService;
import com.Web_CSGO.service.IAppKindService;
import com.Web_CSGO.service.IProductService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;


@Slf4j
@RestController
@RequestMapping("/Product")
@EnableTransactionManagement//事务
public class ProductController extends BaseController {
    @Resource
    private IProductService productService;
    @GetMapping("getProduct")
    public ModelAndView getAdminUserPage(){
        return new ModelAndView("main/getProductList");
    }
    /***
     *
     * @param page
     * @param rows
     * @return
     * 获取管理用户列表
     */
    @PostMapping("getProductList")
    public Object getProductList(Integer page, Integer rows, Product product, String start, String end){
        System.out.println(product);
        List<Product> products = new ArrayList<>();
        /***********分页部分*************/
        JSONObject object = new JSONObject();
        object.put("current",page);
        object.put("size",rows);
        Integer current = (Integer)setPage(object).get("current");
        if (null == current) {
            current = 0;
        }
        //每页的大小
        Integer size = (Integer)setPage(object).get("size");
        if (null == size) {
            size = 10;
        }
        Page<Object> objectPage = new Page<>(current, size);
        /***********分页部分*************/
        products = productService.getProduct(objectPage,product);
        JSONObject returnJson = new JSONObject();
        returnJson.put("rows",products);//每页条数
        returnJson.put("total",objectPage.getTotal());//分页总条数
        return returnJson;
    }
    @PostMapping("delProduct")
    public Object delProduct(String extendId){
        System.out.println(extendId);
        JSONObject returnJson = new JSONObject();
        boolean remove =   productService.removeById(extendId);
        try{
            if (remove){
                returnJson = setSuccessJSONObject(HttpCode.SUCCESS, "", "删除成功!");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return setSuccessJSONObject(HttpCode.BAD_REQUEST, "","删除失败!");
        }
        return returnJson;
    }
    @PostMapping("editGetAppExtend")
    public Object editGetProduct(String productId){
        Map<String,Object> map = new HashMap();
        Product product =productService.getById(productId);
        map.put("product",product);
        return JSON.toJSON(map);
    }
    @PostMapping("addOrUpProduct")
    public Object addOrUpProduct(Product product){

        JSONObject returnJson = new JSONObject();
        List<Product> products = new ArrayList<>();
        products = productService.getProduct(new Page(),product);
        if(products.size()>0){
            return setSuccessJSONObject(HttpCode.BAD_REQUEST, "","保存失败,用回名存在!");
        }
        if("".equals(product.getProductId())){
            String uuid = UUID.randomUUID().toString();
            product.setProductId(uuid);
        }
        try {
            boolean addOrUp = productService.saveOrUpdate(product);

            if (addOrUp){
                returnJson = setSuccessJSONObject(HttpCode.SUCCESS, "", "保存成功!");
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return setSuccessJSONObject(HttpCode.BAD_REQUEST, "","保存失败!");
        }
        return returnJson;
    }
}