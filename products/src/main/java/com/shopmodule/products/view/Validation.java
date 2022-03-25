package com.shopmodule.products.view;

public class Validation {

    /**
     * Validates productId.
     *
     * @param productId
     */
    static boolean validateId(final String productId) {
        return productId != null && productId.matches("[1-9]{1}|[1-9]{1}[0-9]{1,4}") ? true : false;
    }

    /**
     * Validates price.
     *
     * @param price
     */
    static boolean validatePrice(final String price) {
        return (price.matches("^[1-9]{1}|([1-9]{1}[0-9]{1,5})$")
                || (price.matches("^([1-9]{1}[0-9]{1,5}+.([0-9]{1,5}))$"))) ? true : false;
    }

    /**
     * Validates Size
     *
     * @param size
     */
    static boolean validateSize(final String size) {
        return size.matches("^(?i).*-|s|m|l|-{1}.*");
    }

    /**
     * Validates Quantity
     *
     * @param quantity
     */
    static boolean validateQuantity(final String quantity) {
        return quantity != null && quantity.matches("[1-9]{1}|[1-9]{1}[0-9]{1,4}");
    }

    /**
     * Validates Discount.
     *
     * @param discount
     */
    static boolean validateDiscount(final String discount) {
        return discount.matches("^[0-9]{1}|([1]{1}[0]{1})$") ? true : false;
    }
}
