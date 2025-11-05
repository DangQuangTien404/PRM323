package com.myfirstandroidjava.salesapp.models;

import java.util.List;

public class ProductListResponse {
    private int pageNumber;
    private int pageSize;
    private int totalCount;
    private int totalPages;
    private List<ProductItem> items;

    public int getPageNumber() { return pageNumber; }
    public void setPageNumber(int pageNumber) { this.pageNumber = pageNumber; }

    public int getPageSize() { return pageSize; }
    public void setPageSize(int pageSize) { this.pageSize = pageSize; }

    public int getTotalCount() { return totalCount; }
    public void setTotalCount(int totalCount) { this.totalCount = totalCount; }

    public int getTotalPages() { return totalPages; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }

    public List<ProductItem> getItems() { return items; }
    public void setItems(List<ProductItem> items) { this.items = items; }
}
