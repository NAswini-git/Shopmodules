package com.shopmodule.products.view;

import com.shopmodule.generalinputs.userinput.UserInput;
import com.shopmodule.products.controller.ProductsController;
import com.shopmodule.products.model.Product;
import com.shopmodule.exception.userdefinedexceptions.CustomException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Gets the choice for modification of products by admin.
 *
 * @author AswiniN
 */
public class ProductManager {

    private static final ProductsController PRODUCT_CONTROLLER = new ProductsController();
    private static final Logger LOGGER = Logger.getLogger(ProductManager.class);

    /**
     * Shows the choice to admin.
     */
    public static void showAdminChoice() {

        try {
            final int adminChoice = UserInput.getInstance().getInt("\n 1. INSERT PRODUCT \n 2. VIEW PRODUCTS \n 3. UPDATE PRODUCT \n 4. DELETE PRODUCT \n 5. SEARCH PRODUCT \n 6. EXIT  ");

            if (adminChoice >= 1 && adminChoice < 7) {
                ProductManager.productAlterCategory(adminChoice);
            } else {
                LOGGER.info("Enter choice between 1 to 6.");
            }
            ProductManager.showAdminChoice();
        } catch (NumberFormatException e) {
            LOGGER.warn("Please enter choice between 1 to 6.");
            ProductManager.showAdminChoice();
        }
    }

    /**
     * Routes the admin based on the selected alteration choice.
     *
     * @param adminChoice
     */
    private static void productAlterCategory(final int adminChoice) {
        Product product = new Product();

        switch (adminChoice) {
            case 1:
                product = ProductDetails.getProductDetails();
                insertProduct(product);
                break;

            case 2:
                ProductDetails.showProductDetails();
                ProductManager.showAdminChoice();
                break;

            case 3:
                product = ProductDetails.getUpdateDetails();
                updateProduct(product);
                break;

            case 4:
                product = ProductDetails.getDeleteDetails();
                deleteProduct(product);
                break;

            case 5:
                ProductManager.searchProduct();
                ProductManager.showAdminChoice();
                break;

            case 6:
                System.exit(0);
                break;

            default:
                System.out.println("Enter choice 1 to 6.");
                ProductManager.showAdminChoice();
        }
    }

    /**
     * Inserts the product.
     *
     * @param product
     */
    private static void insertProduct(final Product product) {

        try {

            if (PRODUCT_CONTROLLER.insertProduct(product)) {
                LOGGER.info("Successfully inserted");
            }
        } catch (CustomException e) {
            LOGGER.warn(e);
        }
        ProductManager.showAdminChoice();
    }

    /**
     * Updates the Product.
     *
     * @param product
     */
    private static void updateProduct(final Product product) {

        try {

            if (PRODUCT_CONTROLLER.updateProducts(product)) {
                LOGGER.info("Successfully updated.");
            }
        } catch (CustomException e) {
            LOGGER.warn(e);
        }
        ProductManager.showAdminChoice();
    }

    /**
     * Deletes the product.
     *
     * @param product
     */
    private static void deleteProduct(final Product product) {

        try {
            final String output = PRODUCT_CONTROLLER.deleteProduct(product) ? "Successfully Deleted " : "Something went wrong while deleting";

            LOGGER.info(output);
        } catch (CustomException e) {
            LOGGER.warn(e);
        }
        ProductManager.showAdminChoice();
    }

    /**
     * Searches the Product.
     */
    public static void searchProduct() {

        try {
            List<Product> product = PRODUCT_CONTROLLER.selectProduct(ProductDetails.getProductName());

            for (Product newProduct : product) {
                System.out.println(String.format("Product Name: %s", newProduct.getProductName()));
                System.out.println(String.format("Brand Name: %s", newProduct.getBrandName()));
                System.out.println(String.format("Price: %s", newProduct.getPrice()));
                System.out.println(String.format("Size: %s", newProduct.getSize()));
                System.out.println(String.format("Quantity: %s", newProduct.getQuantity()));
                System.out.println(String.format("Discount: %s", newProduct.getDiscount()).concat("%"));
                System.out.println();
            }
        } catch (CustomException e) {
            LOGGER.warn(e);
        }
    }
}
