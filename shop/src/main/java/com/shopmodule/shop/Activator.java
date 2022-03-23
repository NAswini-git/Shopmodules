package com.shopmodule.shop;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.shopmodule.authentication.view.LoginPortal;

import com.shopmodule.mysqlconnectivity.databaseconnection.DatabaseConnection;
import com.shopmodule.products.controller.RestImpl;
import org.apache.cxf.BusFactory;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import java.util.Map;

/**
 * Application begins from this class.
 *
 * @author AswiniN
 */
@Component(immediate = true, name = "db")
public class Activator  {
    
    private Server server;

    /**
     * Activates the server to implement REST services.
     */
    @Activate
    public void activate(Map<String, String> properties) {

        try {

            DatabaseConnection databaseConnection = new DatabaseConnection();
            databaseConnection.setProperty(properties);
            JAXRSServerFactoryBean bean = new JAXRSServerFactoryBean();
            bean.setAddress("/product");
            bean.setBus(BusFactory.getDefaultBus());
            bean.setProvider(new JacksonJsonProvider());
            bean.setServiceBean(new RestImpl());
            server = bean.create();
            LoginPortal.renderLoginPortal();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Deactivates the server.
     *
     * @throws Exception
     */
    @Deactivate
    public void deactivate() throws Exception {
        if (server != null) {
            server.destroy();
        }
    }
}
