package com.Web_CSGO.common;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Web_CSGO.entity.AdminUser;
import com.Web_CSGO.entity.OcInformationsEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


/**
 * 
 * 
 * @Package: com.*.*.interceptor 
 * @ClassName: AdminInterceptor 
 * @Description:拦截器
 * @author: zk
 * @date: 2019年9月19日 下午2:20:57
 */
public class AdminInterceptor implements  HandlerInterceptor {

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  throws ServletException {
        System.out.println("执行了TestInterceptor的preHandle方法");

        try {
            System.err.println(request.getRequestURI());
            OcInformationsEntity ocInformationsEntity =new OcInformationsEntity();
            //统一拦截（查询当前session是否存在user）(这里user会在每次登陆成功后，写入session)
            AdminUser AdminUser=(AdminUser)request.getSession().getAttribute("AdminUser");
            if(AdminUser==null){
                ocInformationsEntity=(OcInformationsEntity)request.getSession().getAttribute("OcInformationsEntity");
            }
            if(AdminUser!=null||ocInformationsEntity!=null){
                return true;
            }
            if(AdminUser==null&&!"/user/".contains(request.getRequestURI())){
                response.sendRedirect("/LoginController/AdminloginPage");
            }else if(ocInformationsEntity==null&&"/user/".contains(request.getRequestURI())&&!"/".contains(request.getRequestURI())){
                response.sendRedirect("/LoginController/loginPage");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
                      //如果设置为true时，请求将会继续执行后面的操作
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//         System.out.println("执行了TestInterceptor的postHandle方法");
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        System.out.println("执行了TestInterceptor的afterCompletion方法");
    }
    
}