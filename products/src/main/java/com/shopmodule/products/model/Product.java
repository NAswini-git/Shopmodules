package com.shopmodule.products.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * Class for products table.
 *
 * @author AswiniN
 */
public class Product {

    @Pattern(message = "Only Alphabets should be given for product name.", regexp = "^[A-Za-z\\s ]{1,20}$", groups = {ProductSelectChecks.class, ProductInsertChecks.class, ProductUpdateChecks.class})
    private String productName;

    @Pattern(message = "Only Alphabets should be given for brand name.", regexp = "^[A-Za-z\\s ]{1,20}$", groups = {ProductInsertChecks.class, ProductUpdateChecks.class})
    private String brandName;

    @NotNull(message = "Give valid price", groups = {ProductInsertChecks.class})
    private String price;

    @NotNull(message = "Give valid size", groups = {ProductInsertChecks.class})
    private String size;

    @NotEmpty(message = "Give valid quantity", groups = {ProductInsertChecks.class, ProductUpdateChecks.class})
    @Min(value = 1, message = "At least 1 product should be found")
    private String quantity;

    @NotNull(message = "Give valid quantity", groups = {ProductInsertChecks.class})
    private String discount;

    @NotEmpty(message = "Give valid productId", groups = {ProductInsertChecks.class, ProductDeleteChecks.class})
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
