package com.Web_CSGO.common.util;

import com.Web_CSGO.common.support.HttpKit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 *
 */
public class PageUtil{

    private final static String DEFAULT_PAGE_SIZE = "10";

    public PageUtil() {
    }

    public static int getCurPage(Map<String, Object> params) {
        String pageStr = String.valueOf(params.get("curPage"));
        if ("".equals(pageStr) || "null".equals(pageStr) ) {
            return 1;
        }
        return Integer.parseInt(pageStr);
    }

    /**
     * @方法名: getCurPageSize
     * @描述:得到当前分页数
     * @作者:高星
     * @时间:2015-6-9 上午12:25:03
     * @参数:@param request
     * @参数:@return
     * @返回值：int
     */
    public static int getCurPageSize(Map<String, Object> params) {
        String pageStr = String.valueOf(params.get("pageSize"));
        if ("".equals(pageStr) || "null".equals(pageStr)) {
            return Integer.parseInt(DEFAULT_PAGE_SIZE);
        }
        return Integer.parseInt(pageStr);
    }

    public static int getCurPage(HttpServletRequest request) {
        String pageStr = request.getParameter("curPage");
        if (StringUtils.isBlank(pageStr)) {
            return 1;
        }
        return Integer.parseInt(pageStr);
    }

    /**
     * @方法名: getCurPageSize
     * @描述:得到当前分页数
     * @作者:高星
     * @时间:2015-6-9 上午12:25:03
     * @参数:@param request
     * @参数:@return
     * @返回值：int
     */
    public static int getCurPageSize(HttpServletRequest request) {
        String pageStr = request.getParameter("pageSize");
        if (StringUtils.isBlank(pageStr)) {
            return Integer.parseInt(DEFAULT_PAGE_SIZE);
        }
        return Integer.parseInt(pageStr);
    }


    /**
     * @方法名: getCurPageSize
     * @描述:得到当前分页数
     * @时间:2015-6-9 上午12:25:03
     * @参数:@param request
     * @参数:@return
     * @返回值：int
     */
    public static int getCurPageSize(HttpServletRequest request, Integer defaultPagesize) {
        if (null == defaultPagesize) {
            return getCurPageSize(request);
        } else {
            String pageStr = request.getParameter("pageSize");
            if (StringUtils.isBlank(pageStr)) {
                return defaultPagesize;
            } else {
                return Integer.parseInt(pageStr);
            }
        }
    }

    public static Page defaultPage() {
        HttpServletRequest request = HttpKit.getRequest();
        int pageSize = StringUtils.isBlank(request.getParameter("pageSize"))  ? 10 : Integer.valueOf(request.getParameter("pageSize"));     //每页多少条数据
        int currentPage = StringUtils.isBlank(request.getParameter("currentPage"))  ? 0 : Integer.valueOf(request.getParameter("currentPage"));   //当前页
//        String sort = request.getParameter("sort");         //排序字段名称
//        String order = request.getParameter("order");       //asc或desc(升序或降序)
//        if (ToolUtil.isEmpty(sort)) {
//            Page<Object> page = new Page<Object>(currentPage, pageSize);
//
//            return page;
//        } else {
//            Page<Object> page = new Page<Object>(currentPage, pageSize, sort);
//            if (Order.ASC.getDes().equals(order)) {
//                page.setAsc(true);
//            } else {
//                page.setAsc(false);
//            }
                   Page<Object> page = new Page<Object>(currentPage, pageSize);
            return page;

    }





}
