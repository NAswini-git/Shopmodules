package com.shopmodule.products.controller;

import com.shopmodule.products.model.Product;
import com.shopmodule.products.service.RestService;
import com.shopmodule.products.service.RestServiceImpl;

import java.util.List;

/**
 * Controls the flow between view and service implementation for product
 * table.
 *
 * @author AswiniN
 */
public class RestController {

    private static RestService REST_SERVICE = new RestServiceImpl();

    /**
     * Shows the products.
     */
    public List<Product> showProductDetails() {
        return REST_SERVICE.showProductDetails();
    }

    /**
     * Inserts the product.
     *
     * @param products
     */
    public List insertProduct(final Product products) {
        return REST_SERVICE.insertProduct(products);
    }

    /**
     * Deletes the product.
     *
     * @param products
     */
    public List deleteProduct(final Product products) { return REST_SERVICE.deleteProduct(products); }

    /**
     * Updates the product.
     *
     * @param products
     */
    public List updateProducts(final Product products) {
        return REST_SERVICE.updateProduct(products);
    }

    /**
     * Selects a specified product.
     *
     * @param productName
     */
    public List<Product> selectProduct(final String productName) {
        return REST_SERVICE.selectProduct(productName);
    }

    /**
     * Validate Id of the product.
     *
     * @param productId
     */
    public boolean validateId(String productId) {
        return REST_SERVICE.validateId(productId);
    }

    /**
     * Shows the products in appropriate paginated form.
     *
     * @param page
     * @param size
     */
    public List<Product> showPaginatedProductDetails(int page, int size) { return REST_SERVICE.showPaginatedProductDetails(page, size); }
}

