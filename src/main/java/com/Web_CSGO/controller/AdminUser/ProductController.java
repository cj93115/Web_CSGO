package com.Web_CSGO.controller.AdminUser;

import com.Web_CSGO.common.HttpCode;
import com.Web_CSGO.common.base.BaseController;
import com.Web_CSGO.common.util.ToolUtil;
import com.Web_CSGO.entity.Product;
import com.Web_CSGO.service.IProductService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    @PostMapping("editGetProduct")
    public Object editGetProduct(String productId){
        Product product =productService.getById(productId);
        return JSON.toJSON(product);
    }
    @PostMapping("addOrUpProduct")
    public Object addOrUpProduct(Product product){

        JSONObject returnJson = new JSONObject();
        List<Product> products = new ArrayList<>();
        products = productService.getProduct(new Page(), product);
        if (products.size() > 0&&!products.get(0).getProduct_ID().equals(product.getProduct_ID())) {
            return setSuccessJSONObject(HttpCode.BAD_REQUEST, "", "保存失败,名称存在!");
        }
        if("".equals(product.getProduct_ID())){
            String uuid = UUID.randomUUID().toString();
            product.setProduct_ID(uuid);
            product.setProduct_Time(new Date());
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

    @PostMapping("imageUpload")
    @ResponseBody
    public Object filesUpload(@RequestParam(value="file") MultipartFile file) {
       String filename= ToolUtil.filesUpload(file);
       if(file==null){
           return setSuccessJSONObject(HttpCode.BAD_REQUEST, "","保存失败!");
       }
        return   setSuccessJSONObject(HttpCode.SUCCESS, filename, "保存成功!");

    }
}
