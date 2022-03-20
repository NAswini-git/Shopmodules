package com.shopmodule.products.controller;

import com.shopmodule.products.model.Product;

import java.util.List;

/**
 * Interface to provide modification in products table via REST services.
 *
 * @author AswiniN
 */
public interface Rest {

    List<Product> showProductDetails(int page, int limit);

    boolean insertProduct(Product product);

    boolean deleteProduct(Product product);

    boolean updateProduct(Product product);

    List<Product> searchProduct(String productName);

}
