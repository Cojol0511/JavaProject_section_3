package com.example.Section3Project.model;

import java.util.Date;

public class Product {

    private String id;
    private String name;
    private Integer price;
    private String description;
    private Date createdTime;
    private Date updatedTime;
    private Boolean status;
    private Category category;

    public Product(String id, String name, Integer price, String description, Date createdTime, Date updatedTime, Boolean status, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.status = status;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
