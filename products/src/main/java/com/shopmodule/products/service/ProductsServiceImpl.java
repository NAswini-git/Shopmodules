package com.shopmodule.products.service;

import com.shopmodule.products.dao.ProductsAccessDAO;
import com.shopmodule.products.exception.ProductNotFoundException;
import com.shopmodule.products.exception.UpdateFailedException;
import com.shopmodule.products.model.Product;
import com.shopmodule.exception.userdefinedexceptions.InsertionFailedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementing class for data modification in product table.
 *
 * @author AswiniN
 */

public class ProductsServiceImpl implements ProductsService {

    private static final ProductsAccessDAO PRODUCTS_DAO = new ProductsAccessDAO();

    /**
     * Shows the products available.
     */
    public List<Product> showProductDetails() {
        List<Product> product = PRODUCTS_DAO.selectAllProducts();

        if (!product.isEmpty()) {
            return product;
        }
        throw new ProductNotFoundException("Products are not available in the table");
    }

    /**
     * Shows the products in appropriate pages based on user input.
     *
     * @param page
     * @param limit
     * @return
     */
    public List<Product> showPaginatedProductDetails(final int page, final int limit) {
        List<Product> product = PRODUCTS_DAO.selectAllProducts();
        int start = ((page - 1) * limit);
        int end = start + limit;

        if (start < product.size()) {
            if (end < product.size()) {
                return product.subList(start, end);
            } else if ((start + limit) >= product.size()) {
                return product.subList(start, product.size());
            }
        }
        return new ArrayList<>();
    }

    /**
     * Validates Insert.
     *
     * @param product
     */
    public boolean validateInsert(final Product product) {
        List<Product> products = PRODUCTS_DAO.selectAllProducts();

        for (Product listOfProducts : products) {

            if (listOfProducts.getProductId().equals(product.getProductId())) {
                return true;
            } else if (listOfProducts.getProductName().equals(product.getProductName()) && listOfProducts.getBrandName().equals(product.getBrandName())) {
                System.out.println("Product already available");

                return true;

            }
        }
        return false;
    }

    /**
     * Inserts the product.
     *
     * @param product
     */
    public boolean insertProduct(final Product product) {

        if (!validateInsert(product)) {

            if (PRODUCTS_DAO.insertProduct(product)) {
                return true;
            }
        }
        throw new InsertionFailedException("Product is already available in the list! You can only update it.");
    }

    /**
     * Deletes the product.
     *
     * @param product
     */
    public boolean deleteProduct(final Product product) {
        boolean isDeleted = PRODUCTS_DAO.deleteProduct(product);

        if (isDeleted) {
            return true;
        }
        throw new ProductNotFoundException("Given product is not available in the table");
    }

    /**
     * Updates the product.
     *
     * @param product
     */
    public boolean updateProduct(final Product product) {
        boolean isUpdated = PRODUCTS_DAO.updateProduct(product);

        if (isUpdated) {
            return true;
        }
        throw new UpdateFailedException("Product not found! So failed to update the product.");
    }

    /**
     * Selects a specific product.
     *
     * @param productName
     */
    public List<Product> selectProduct(String productName) {

        List<Product> product = PRODUCTS_DAO.selectProduct(productName);

        if (!product.isEmpty()) {
            return product;
        }
        throw new ProductNotFoundException("Given product is not available in the table");
    }

    /**
     * Validates Id
     *
     * @param productId
     */
    public boolean validateId(String productId) {
        List<Product> products = PRODUCTS_DAO.selectAllProducts();

        for (Product listOfProducts : products) {
            if (listOfProducts.getProductId().equals(productId)) {
                return false;
            }
        }
        return true;
    }
}
