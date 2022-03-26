package com.shopmodule.products.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopmodule.mysqlconnectivity.databaseconnection.DatabaseConnection;
import com.shopmodule.exception.userdefinedexceptions.DatabaseAccessDeniedException;
import com.shopmodule.products.exception.UpdateFailedException;
import com.shopmodule.products.model.Product;

/**
 * To modify the product table using SQL Queries.
 *
 * @author AswiniN
 */
public class ProductsAccessDAO {

    /**
     * Inserts new products to the table.
     *
     * @param product
     */
    public boolean insertProduct(final Product product) {
        final String insertQuery = "INSERT INTO products VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, product.getProductId());
                preparedStatement.setString(2, product.getProductName());
                preparedStatement.setString(3, product.getBrandName());
                preparedStatement.setString(4, product.getPrice());
                preparedStatement.setString(5, product.getSize());
                preparedStatement.setString(6, product.getQuantity());

                preparedStatement.executeUpdate();
            }
            final String insertDiscount = "INSERT INTO product_discount (product_id,discount) VALUES (?, ?) ";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertDiscount)) {
                preparedStatement.setString(1, product.getProductId());
                preparedStatement.setString(2, product.getDiscount());

                preparedStatement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            throw new DatabaseAccessDeniedException("Error occurred while inserting the product. Query may be invalid.");
        }
    }

    /**
     * Deletes the product from table specified by the user.
     *
     * @param product
     */
    public boolean deleteProduct(final Product product) {
        final String deleteQuery = "DELETE FROM products WHERE product_name = ? and brand_name = ? and  size = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getBrandName());
            preparedStatement.setString(3, product.getSize());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DatabaseAccessDeniedException("Query may be invalid. So database access failed!");
        }
    }

    public boolean delete(final Product product) {
        final String deleteQuery = "DELETE FROM products WHERE product_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setString(1, product.getProductId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DatabaseAccessDeniedException("Query may be invalid. So database access failed!");
        }
    }

    /**
     * Updates the record of the table.
     *
     * @param product
     */
    public boolean updateProduct(final Product product) {
        final StringBuilder stringBuilder = new StringBuilder();

        String string = stringBuilder.append("UPDATE products SET").toString();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(string)) {

            if (product.getPrice() != null) {
                stringBuilder.append(" price = '").append(product.getPrice()).append("'");
            } else if (product.getSize() != null) {
                stringBuilder.append(" size = '").append(product.getSize()).append("'");
            } else if (product.getQuantity() != null) {
                stringBuilder.append(" quantity = '").append(product.getQuantity()).append("'");
            }
            stringBuilder.append(" WHERE product_name = '").append(product.getProductName()).append("'")
                    .append("and brand_name = '").append(product.getBrandName()).append("'");
            return preparedStatement.executeUpdate(stringBuilder.toString()) > 0;
        } catch (SQLException e) {
            throw new UpdateFailedException("Query may be invalid. Update Failed!");
        }
    }

    /**
     * Selects a specific product.
     *
     * @param productName
     */
    public List<Product> selectProduct(String productName) {

        final String selectQuery = "SELECT p.product_id, p.product_name, p.brand_name, p.price, p.size, p.quantity, pd.discount  FROM products As p "
                + " INNER JOIN product_discount AS pd WHERE p.product_name LIKE '" + productName
                + "%' and p.product_id = pd.product_id";
        final List<Product> productsList = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet productsResultSet = preparedStatement.executeQuery()) {

            while (productsResultSet.next()) {
                final Product product = new Product();

                product.setProductId(productsResultSet.getString(1));
                product.setProductName(productsResultSet.getString(2));
                product.setBrandName(productsResultSet.getString(3));
                product.setPrice(productsResultSet.getString(4));
                product.setSize(productsResultSet.getString(5));
                product.setQuantity(productsResultSet.getString(6));
                product.setDiscount(productsResultSet.getString(7));
                productsList.add(product);
            }
            return productsList;
        } catch (SQLException e) {
            throw new DatabaseAccessDeniedException("Query may be invalid. So database access failed!");
        }
    }

    /**
     * Selects all data from products table.
     */
    public List<Product> selectAllProducts() {
        final List<Product> productsList = new ArrayList<>();
        final String selectQuery = "SELECT p.product_id, p.product_name, p.brand_name, p.price, p.size, p.quantity, pd.discount FROM products As p "
                + "INNER JOIN product_discount AS pd ON p.product_id = pd.product_id ";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet productsResultSet = preparedStatement.executeQuery()) {

            while (productsResultSet.next()) {
                Product product = new Product();

                product.setProductId(productsResultSet.getString(1));
                product.setProductName(productsResultSet.getString(2));
                product.setBrandName(productsResultSet.getString(3));
                product.setPrice(productsResultSet.getString(4));
                product.setSize(productsResultSet.getString(5));
                product.setQuantity(productsResultSet.getString(6));
                product.setDiscount(productsResultSet.getString(7));

                productsList.add(product);
            }
            return productsList;
        } catch (SQLException e) {
            throw new DatabaseAccessDeniedException("Query may be invalid. So database access failed!");
        }
    }
}
