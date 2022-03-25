package com.shopmodule.products.service;

import com.shopmodule.products.model.Product;

import java.util.List;

public interface RestService {
    List showProductDetails();

    List showPaginatedProductDetails(int page, int limit);

    List selectProduct(String productName);

    List insertProduct(Product product);

    List deleteProduct(Product product);

    List updateProduct(Product product);

    boolean validateId(String productId);

}
