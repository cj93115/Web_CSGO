package com.Web_CSGO.common.base.tips;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * 返回给前台的提示（最终转化为json形式）
 *
 */
public abstract class Tip {
    //返回代码
    protected int code;
    //返回信息
    protected String msg;


    // 总数
    protected long total;

    //总页数
    protected long totalPage;

    //当前页
    protected Long currentPage;

    //每页数据量
    protected Long pageSize;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }
}
