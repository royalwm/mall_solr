package com.search.entity;

import java.util.List;

public class ResultSearch {
    private long total;
    private int curPage;
    private int page;
    private int pageSize;
    private List<Items> itemList;


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPage() {
        return (int) Math.ceil(this.total / pageSize);
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long numFound) {
        this.total = numFound;
    }

    public List<Items> getItemList() {
        return itemList;
    }

    public void setItemList(List<Items> itemList) {
        this.itemList = itemList;
    }

}
