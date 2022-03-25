package com.shopmodule.products.service;

import com.shopmodule.exception.userdefinedexceptions.InsertionFailedException;
import com.shopmodule.products.dao.ProductsAccessDAO;
import com.shopmodule.products.exception.ProductNotFoundException;
import com.shopmodule.products.exception.UpdateFailedException;
import com.shopmodule.products.model.Product;

import java.util.ArrayList;
import java.util.List;
/**
 * Implementing class for data modification in product table.
 *
 * @author AswiniN
 */

public class RestServiceImpl implements RestService {

    private static final ProductsAccessDAO PRODUCTS_DAO = new ProductsAccessDAO();

    /**
     * Shows the products available.
     */
    public List showProductDetails() {
        List product = PRODUCTS_DAO.selectAllProducts();

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
    public List showPaginatedProductDetails(final int page, final int limit) {
        List product = PRODUCTS_DAO.selectAllProducts();
        int start = ((page - 1) * limit);
        int end = start + limit;

        if (page*limit > product.size() ){
            product = new ArrayList();
            product.add("Page is beyond the limit");
            return product;
        }
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
    public List insertProduct(final Product product) {
        List list = new ArrayList();
        boolean isInserted = false;

        if (!validateInsert(product)) {
            isInserted = PRODUCTS_DAO.insertProduct(product);
            list.add(isInserted);
            return list;
        }  else {
            list.add("Product is already available in the list! You can only update it.");
            return list;
        }
    }

    /**
     * Deletes the product.
     *
     * @param product
     */
    public List deleteProduct(final Product product) {
        List list = new ArrayList();
        boolean isDeleted = PRODUCTS_DAO.delete(product);
        //boolean isDeleted = PRODUCTS_DAO.deleteProduct(product);
        if (isDeleted) {
            return list;
        } else {
            list.add("Given product is not available in the table");
            return list;
        }
    }

    /**
     * Updates the product.
     *
     * @param product
     */
    public List updateProduct(final Product product) {
        List list = new ArrayList();
        boolean isUpdated = PRODUCTS_DAO.updateProduct(product);
        if (isUpdated) {
            list.add(isUpdated);
            return list;
        } else {
            list.add("Product not found! So failed to update the product");
            return list;
        }
    }

    /**
     * Selects a specific product.
     *
     * @param productName
     */
    public List selectProduct(String productName) {
        List product = PRODUCTS_DAO.selectProduct(productName);

        if (!product.isEmpty()) {
            return product;
        } else {
            product.add("Given product is not available in the table");
            return product;
        }
      }

    /**
     * Validates Id
     *
     * @param productId
     */
    public boolean validateId(final String productId) {
        List<Product> products = PRODUCTS_DAO.selectAllProducts();

        for (Product listOfProducts : products) {
            if (listOfProducts.getProductId().equals(productId)) {
                return false;
            }
        }
        return true;
    }
}
