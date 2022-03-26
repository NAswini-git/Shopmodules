package com.shopmodule.products.controller;

import com.shopmodule.products.model.Product;
import com.shopmodule.products.service.ProductsService;
import com.shopmodule.products.service.ProductsServiceImpl;

import java.util.List;

/**
 * Controls the flow between view and service implementation for product
 * table.
 *
 * @author AswiniN
 */
public class ProductsController {

    private static ProductsService PRODUCTS_SERVICE = new ProductsServiceImpl();

    /**
     * Shows the products.
     */
    public List<Product> showProductDetails() {
        return PRODUCTS_SERVICE.showProductDetails();
    }

    /**
     * Inserts the product.
     *
     * @param products
     */
    public boolean insertProduct(final Product products) {
        return PRODUCTS_SERVICE.insertProduct(products);
    }

    /**
     * Deletes the product.
     *
     * @param products
     */
    public boolean deleteProduct(final Product products) {
        return PRODUCTS_SERVICE.deleteProduct(products);
    }

    /**
     * Updates the product.
     *
     * @param products
     */
    public boolean updateProducts(final Product products) {
        return PRODUCTS_SERVICE.updateProduct(products);
    }

    /**
     * Selects a specified product.
     *
     * @param productName
     */
    public List<Product> selectProduct(final String productName) {
        return PRODUCTS_SERVICE.selectProduct(productName);
    }

    /**
     * Validate Id of the product.
     *
     * @param productId
     */
    public boolean validateId(String productId) {
        return PRODUCTS_SERVICE.validateId(productId);
    }

    /**
     * Shows the products in appropriate paginated form.
     *
     * @param page
     * @param size
     */
    public List<Product> showPaginatedProductDetails(int page, int size) {
        return PRODUCTS_SERVICE.showPaginatedProductDetails(page, size);
    }
}
