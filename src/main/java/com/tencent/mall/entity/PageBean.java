package com.tencent.mall.entity;

/**
 * 分页Model类
 *
 * @author Jarry
 */
public class PageBean {


    /**
     * 第几页
     */
    private int pageNum;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 起始页
     */
    private int start;
    /**
     * 查询参数
     */
    private String query;

    public PageBean() {
    }

    public PageBean(int pageNum, int pageSize, String query) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.query = query;
    }

    public PageBean(int pageNum, int pageSize) {
        super();
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return (pageNum - 1) * pageSize;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
