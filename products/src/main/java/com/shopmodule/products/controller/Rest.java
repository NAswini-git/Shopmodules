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

    List insertProduct(Product product);

   // List deleteProduct(Product product);

    List updateProduct(Product product);

    List<Product> searchProduct(String productName);

    List deleteProduct(String productId);

}
