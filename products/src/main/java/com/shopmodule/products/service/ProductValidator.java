package com.shopmodule.products.service;

import com.shopmodule.products.model.Product;
import com.shopmodule.products.model.ProductInsertChecks;
import com.shopmodule.products.model.ProductSelectChecks;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.ws.rs.ext.Provider;
import org.hibernate.validator.HibernateValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Provider
public class ProductValidator {

    private static final ValidatorFactory FACTORY = Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory();
    private static final Validator VALIDATOR = FACTORY.getValidator();

    /**
     * Validates Selection of product.
     * @param product
     * @return
     */
    public static List checkProduct(Product product, Class optedclass) {
        final List violationList = new ArrayList<>();
        final Set<ConstraintViolation<Product>> constraintViolations = VALIDATOR.validate(product, optedclass);

        for (ConstraintViolation<Product> message : constraintViolations) {
           violationList.add(message.getMessage());
        }
        return violationList;
    }
}
