package com.shopmodule.products.controller;

import com.shopmodule.products.model.Product;
import org.osgi.service.component.annotations.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 *To fetch and modify products via REST services.
 *
 * @author AswiniN
 */

@Component(immediate = true, service = Rest.class)
public class RestImpl implements Rest {

    private static final ProductsController PRODUCTS_CONTROLLER = new ProductsController();

    /**
     * Shows the products available in the table.
     *
     * @param page
     * @param limit
     */
    @Path("/allproducts")
    @Produces("application/json")
    @GET
    public List<Product> showProductDetails(@QueryParam("page") int page,
                                            @QueryParam("limit") int limit) {

        if (page == 0 && limit == 0) {
            return PRODUCTS_CONTROLLER.showProductDetails();
        } else if (page <=0 || limit <=0) {
           return new ArrayList<>();
       }
        return PRODUCTS_CONTROLLER.showPaginatedProductDetails(page, limit);
    }


    /**
     * Inserts the product.
     *
     * @param product
     */
    @Path("/insertproduct")
    @Consumes("application/json")
    @POST
    public boolean insertProduct(final Product product) {
        return PRODUCTS_CONTROLLER.insertProduct(product);
    }

    /**
     * Deletes the product.
     *
     * @param product
     */
    @Path("/deleteproduct")
    @Consumes("application/json")
    @DELETE
    public boolean deleteProduct(final Product product) {
        return PRODUCTS_CONTROLLER.deleteProduct(product);
    }

    /**
     * Updates the product.
     *
     * @param product
     */
    @Path("/updateproduct")
    @Consumes("application/json")
    @PUT
    public boolean updateProduct(final Product product) {
        return PRODUCTS_CONTROLLER.updateProducts(product);
    }

    /**
     * Searches a specific product.
     *
     * @param productName
     */
    @Path("/searchproduct")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<Product> searchProduct(@QueryParam("productname") final String productName) {
        System.out.println("pdt"+productName);
        return PRODUCTS_CONTROLLER.selectProduct(productName);
    }
}