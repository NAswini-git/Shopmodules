package com.shopmodule.products.controller;

import com.shopmodule.products.model.*;
import com.shopmodule.products.service.ProductValidator;

import jakarta.validation.Valid;
import org.osgi.service.component.annotations.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

/**
 * To fetch and modify products via REST services.
 *
 * @author AswiniN
 */

@Component(immediate = true, service = Rest.class)
public class RestImpl implements Rest {

    private static final RestController REST_CONTROLLER = new RestController();

    /**
     * Shows the products available in the table.
     *
     * @param page
     * @param limit
     */
    @Path("/allProducts")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<Product> showProductDetails(@Valid @QueryParam("page") final int page,
                                            @Valid @QueryParam("limit") final int limit) {

        if (page == 0 && limit == 0) {
            return REST_CONTROLLER.showProductDetails();
        } else if (page <= 0 || limit <= 0) {
            return new ArrayList<>();
        }
        return REST_CONTROLLER.showPaginatedProductDetails(page, limit);
    }

    /**
     * Inserts the product.
     *
     * @param product
     */
    @Path("/insertProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public List insertProduct(@Valid final Product product) {
        List violationList = ProductValidator.checkProduct(product, ProductInsertChecks.class);

        if (!violationList.isEmpty()) {
            List list = new ArrayList<>();
            list.add(violationList);
            return list;
        }
        return REST_CONTROLLER.insertProduct(product);
    }

    /**
     * Deletes the product.
     *
     * @param productId
     */
    @Path("/deleteProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @DELETE
    public List deleteProduct(@Valid @QueryParam("productId") final String productId) {
        Product product = new Product();
        product.setProductId(productId);
        List violationList = ProductValidator.checkProduct(product, ProductDeleteChecks.class);

        if (!violationList.isEmpty()) {
            List list = new ArrayList<>();
            list.add(violationList);
            return list;
        }
        return REST_CONTROLLER.deleteProduct(product);
    }

    /**
     * Updates the product.
     *
     * @param product
     */
    @Path("/updateProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    public List updateProduct(@Valid final Product product) {
        List violationList = ProductValidator.checkProduct(product, ProductUpdateChecks.class);

        if (!violationList.isEmpty()) {
            List list = new ArrayList<>();
            list.add(violationList);
            return list;
        }
        return REST_CONTROLLER.updateProducts(product);
    }

    /**
     * Searches a specific product.
     *
     * @param productName
     */
    @Path("/searchProduct")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List searchProduct(@Valid @QueryParam("productName") final String productName) {
        Product product = new Product();

        product.setProductName(productName);
        List list = ProductValidator.checkProduct(product, ProductSelectChecks.class);
        if (list.isEmpty()) {
            return REST_CONTROLLER.selectProduct(productName);
        }
        return list;
    }
}