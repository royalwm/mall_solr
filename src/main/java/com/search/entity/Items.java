package com.search.entity;

import org.apache.commons.lang3.StringUtils;

/**
 * @author wangmeng
 *
 */
public class Items {
    private String id;
    private String title;
    private String sellPoint;
    private String price;
    private String image;
    private String categoryName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String[] getImages() {
        if (StringUtils.isNotBlank(image)) {
           String[] images = image.split(",");
           return images;
        }
        return null;
    }
}
