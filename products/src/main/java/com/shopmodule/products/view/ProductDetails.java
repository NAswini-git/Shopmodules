package com.shopmodule.products.view;

import com.shopmodule.products.model.Product;
import com.shopmodule.generalinputs.userinput.UserInput;
import com.shopmodule.products.controller.ProductsController;
import com.shopmodule.exception.userdefinedexceptions.CustomException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * To modify the data of products in the table.
 * 
 * @author AswiniN
 */
public class ProductDetails {

    private static final ProductsController PRODUCT_CONTROLLER = new ProductsController();
    private static final ProductDetails PRODUCT_DETAILS = new ProductDetails();
    private static final Logger LOGGER = Logger.getLogger(ProductDetails.class);
    private static final UserInput USER_INPUT = UserInput.getInstance();
    
    /**
     * Gets ProductDetails.
     * 
     * @return product
     */
    public static Product getProductDetails() {
        final Product product = new Product();

        product.setProductId(ProductDetails.getProductId());
        product.setProductName(ProductDetails.getProductName());
        product.setBrandName(ProductDetails.getBrandName());
        product.setPrice(ProductDetails.getPrice());
        product.setSize(ProductDetails.getSize());
        product.setQuantity(PRODUCT_DETAILS.getQuantity());
        product.setDiscount(PRODUCT_DETAILS.getDiscount());
        
        return product;
    }

    /**
     * Gets Product Id.
     */
    public static String getProductId() {
        final String productId = goToAdminChoice(USER_INPUT.getString("Enter Product Id:  {Press 00 to go back to main menu}"));

        if (Validation.validateId(productId)) {

            if (PRODUCT_CONTROLLER.validateId(productId)) {
                return productId;
            } else {
                LOGGER.info("Product Id already exists. Give a new Id.");
                return ProductDetails.getProductId();
            }
        } else {
            LOGGER.warn("Give a valid product Id");
            return ProductDetails.getProductId();
        }
    }

    /**
     * Shows admin choice.
     * 
     * @param choice
     */
    private static String goToAdminChoice(final String choice) {

        if ("00".equals(choice)) {
            ProductManager.showAdminChoice();
        }
        return choice;
    }

    /**
     * Gets Product name.
     */
    public static String getProductName() {
       final String productName = goToAdminChoice(USER_INPUT.getString("Enter Product Name: " ));
       
        if (com.shopmodule.validation.commonvalidation.Validation.validateName(productName)) {
            return productName;
        } else {
            LOGGER.warn("Give a valid product name");
            return ProductDetails.getProductName();
        }
    }

    /**
     * Gets Brand name.
     */
    private static String getBrandName() {
        final String brandName = goToAdminChoice(USER_INPUT.getString("Enter Brand Name {Press 00 to go back to main menu}" ));
      
        if (com.shopmodule.validation.commonvalidation.Validation.validateName(brandName)) {
            return brandName;
        } else {
            LOGGER.warn("Give a valid brand name");
            return ProductDetails.getBrandName();
        }
    }

    /**
     * Gets Price.
     */
    private static String getPrice() {
        final String price = goToAdminChoice(Double.toString(USER_INPUT.getDouble("Enter Price: {Press 00 to go back to main menu}")));
       
        if (Validation.validatePrice(price)) {
            return price;
        } else {
            LOGGER.warn("Give a valid price");
            return ProductDetails.getPrice();
        }
    }

    /**
     * Gets Size.
     */
    private static String getSize() {
        final String size = goToAdminChoice(USER_INPUT.getString("Enter Size (S, M, L) {Press 00 to go back to main menu}" ));
       
        if (Validation.validateSize(size)) {
            return size;
        } else {
            LOGGER.warn("Give valid size as input");
            return ProductDetails.getSize();
        }
    }

    /**
     * Gets Quantity.
     */
    private String getQuantity() {
        final String quantity = goToAdminChoice(Integer.toString(USER_INPUT.getInt("Enter quantity:  {Press 00 to go back to main menu}")));

        if (Validation.validateQuantity(quantity)) {
            return quantity;
        } else {
            LOGGER.warn("Give valid quantity in Integers.");
            return PRODUCT_DETAILS.getQuantity();
        }
    }

    /**
     * Gets Discount.
     */
    private String getDiscount() {
        final String discount = goToAdminChoice(Integer.toString(USER_INPUT.getInt("Enter discount  {Press 00 to go back to main menu}" )));
     
        if (Validation.validateDiscount(discount)) {
            return discount;
        } else {
            LOGGER.warn("Please give valid discount only in numbers");
            return PRODUCT_DETAILS.getDiscount();
        }
    }

    /**
     * Deletes product.
     */
    public static Product getDeleteDetails() {
        final Product products = new Product();

        products.setProductName(ProductDetails.getProductName());
        products.setBrandName(ProductDetails.getBrandName());
        products.setSize(ProductDetails.getSize());
        
        return products;
    }

    /**
     * Updates product.
     */
    public static Product getUpdateDetails() {
        final Product product = new Product();
        int updateChoice = USER_INPUT.getInt("Update Criteria: 1. Price 2. Quantity 3. Size");

        product.setProductName(ProductDetails.getProductName());
        product.setBrandName(ProductDetails.getBrandName());
        
        switch (updateChoice) {
        case 1:
            product.setPrice(ProductDetails.getPrice());
            break;

        case 2:
            product.setQuantity(PRODUCT_DETAILS.getQuantity());
            break;

        case 3:
            product.setSize(ProductDetails.getSize());
            break;

        default:
            System.out.println("Enter choice between 1 to 3.");
            ProductDetails.getUpdateDetails();
        }
        return product;
    }

    /**
     * Shows the products available in the database.
     */
    public static void showProductDetails() {

        try {
           List<Product> product = PRODUCT_CONTROLLER.showProductDetails();

            for (Product newProduct : product) {
                System.out.println(String.format("Product Id: %s", newProduct.getProductId()));
                System.out.println(String.format("Product Name: %s", newProduct.getProductName()));
                System.out.println(String.format("Brand Name: %s", newProduct.getBrandName()));
                System.out.println(String.format("Price: %s", newProduct.getPrice()));
                System.out.println(String.format("Size: %s", newProduct.getSize()));
                System.out.println(String.format("Quantity: %s", newProduct.getQuantity()));
                System.out.println(String.format("Discount: %s", newProduct.getDiscount()).concat("%"));
                System.out.println();
            }
        } catch (CustomException e) {
            System.out.println(e);
        }
    }
}
