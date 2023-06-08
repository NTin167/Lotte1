package com.example.lotte.response;

import java.util.List;

public class PaginationResponse<T> {
    private List<T> content;
    private long totalItems;
    private int totalPages;
    private int currentPage;

    // Constructors, getters, and setters

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public PaginationResponse() {
    }

    public PaginationResponse(List<T> content, long totalItems, int totalPages, int currentPage) {
        this.content = content;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }
}