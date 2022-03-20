package com.shopmodule.products.service;

import com.shopmodule.products.model.Product;

import java.util.List;

/**
 * Interface to provide modification in products table.
 * 
 * @author AswiniN
 */
public interface ProductsService {
    List<Product> showProductDetails();

    boolean insertProduct(Product product);

    boolean deleteProduct(Product product);

    boolean updateProduct(Product product);

    List<Product> selectProduct(String productName);

    boolean validateId(String productId);

    List<Product> showPaginatedProductDetails(int page, int limit);
}
