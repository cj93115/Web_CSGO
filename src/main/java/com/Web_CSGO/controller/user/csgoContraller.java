package com.Web_CSGO.controller.user;

import com.Web_CSGO.entity.Appextend;
import com.Web_CSGO.entity.Appproduct;
import com.Web_CSGO.service.IAppextendService;
import com.Web_CSGO.service.IAppproductService;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("csgo")
public class csgoContraller {

    @Resource
    private IAppproductService appproductService;
    @Resource
    private IAppextendService appextendService;


    @GetMapping("kaixiang")
    public ModelAndView getAdminUserPage(String extendId, ModelMap modelMap) {
        Appextend appextend = appextendService.getById(extendId);
        Map<String, Object> map = new HashMap<>();
        map.put("extend_ID", extendId);
        List<Appproduct> appproducts = appproductService.selectByMap(map);
        Double count = appproductService.selectExtendCount(extendId);//查询箱子物品总数
        BigDecimal decimalcount = new BigDecimal(count);
        for (Appproduct p : appproducts) {
            //计算物品概率
            if (count != 0) {
                BigDecimal decimal = new BigDecimal(p.getProductCount()).multiply(new BigDecimal(100));
                BigDecimal decimalResout = decimal.divide(decimalcount, 3, RoundingMode.HALF_UP);
                p.setProductPosition(decimalResout.stripTrailingZeros().toPlainString() + "%");
            }else {
                p.setProductPosition(0 + "%");
            }
        }
        modelMap.addAttribute("appextend", appextend);
        modelMap.addAttribute("appproducts", appproducts);
        return new ModelAndView("main/user/open", modelMap);
    }

    @PostMapping("lingjiang")
    public Appproduct kaijiang (String extendId){
        Map<String, Object> map = new HashMap<>();
        map.put("extend_ID", extendId);
        List<Appproduct> appproducts = appproductService.selectByMap(map);
        Double count = appproductService.selectExtendCount(extendId);//查询箱子物品总数
        return  lingjiang(appproducts,count);
    }





    public Appproduct lingjiang(List<Appproduct> appproducts, double count) {
        double nub = Math.random() * count;
        double zhong=0;
       for (Appproduct pr:appproducts){

           double shu=pr.getProductCount();
           if(zhong<=nub && nub<=(shu+zhong)){
                   return pr;
           }
           zhong +=pr.getProductCount();
       }
        return null;
    }

}