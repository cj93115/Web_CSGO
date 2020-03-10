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
import net.coobird.thumbnailator.Thumbnails;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
    public Object filesUpload(@RequestParam(value="file") MultipartFile file,HttpServletRequest request) {
        File saveDir;
        String imgUrl="";
        // 判断文件是否为空
        if (!file.isEmpty())
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
              //  String filePath =  request.getSession().getServletContext().getRealPath("/");//获取项目路径
              //  String substring = filePath.substring(0, 2);//分割盘符拿到根目录
                String substring = "C:";//分割盘符拿到根目录

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//设置日期格式
                String realName = df.format(new Date()).toString().replaceAll("[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]", "")
                        .replace(" ", "");//取日期去掉空格和标点符号

                // 1 构建存放路径
                File fileImg = new File("/uploads");
                if (!fileImg.exists()) {
                    fileImg.mkdirs();//创建文件夹
                    // 2 创建提示文本文本
                    String pathTxt = substring + "/uploads/该文件为图片文件夹.txt";
                    //如果文件夹下没有 提示文件.txt 就会创建该文件
                    BufferedWriter bw = new BufferedWriter(new FileWriter(pathTxt));
                    bw.close();//一定要关闭文件
                }
                //图片尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量
                String fileName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));//获取文件后缀，更改文件名
                imgUrl = substring + "/uploads/" + realName + fileName;
                saveDir = new File(imgUrl);
                file.transferTo(saveDir);   // 转存文件
            } catch (Exception e) {
                log.error(e.getMessage());
                return setSuccessJSONObject(HttpCode.BAD_REQUEST, "","保存失败!");
            }
        return setSuccessJSONObject(HttpCode.SUCCESS, imgUrl, "保存成功!");
    }
}
