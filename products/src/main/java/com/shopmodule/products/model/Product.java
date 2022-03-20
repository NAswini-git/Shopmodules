package com.shopmodule.products.model;

/**
 * Class for products table.
 * 
 * @author AswiniN
 */
public class Product {
    private String productName;
    private String brandName;
    private String price;
    private String size;
    private String quantity;
    private String discount;
    private String getProductId;

    public String getProductId() {
        return getProductId;
    }

    public void setProductId(String productId) {
        this.getProductId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String string) {
        this.quantity = string;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String toString() {
        return String.format("%s %s %s %s %s %s %s ", getProductId, productName, brandName, price, size, quantity, discount);
    }

}
