package com.Web_CSGO.common.base.tips;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 分页结果的封装(for Bootstrap Table)
 *
 */
public class PageInfoBT<T> {

    // 结果集
    private List<T> rows;

    // 总数
    private long total;

    //总页数
    private long totalPage;

    //当前页
    private Long currentPage;

    //每页数据量
    private Long pageSize;

    public PageInfoBT(Page<T> page) {
        this.rows = page.getRecords();
        this.total = page.getTotal();
    }
    public PageInfoBT(Page<T> page,long limit,long current) {
        this.rows = page.getRecords();
        this.total = page.getTotal();
        this.pageSize = limit;
        this.totalPage = 0==page.getTotal()%limit?page.getTotal()/limit:page.getTotal()/limit+1;
        this.currentPage = current;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotalPage(){
        return totalPage;
    }

    public void setTotalPage(long totalPage){
        this.totalPage = totalPage;
    }

    public Long getCurrentPage(){
        return currentPage;
    }

    public void setCurrentPage(Long currentPage){
        this.currentPage = currentPage;
    }

    public long getPageSize(){
        return pageSize;
    }

    public void setPageSize(Long pageSize){
        this.pageSize = pageSize;
    }
}
