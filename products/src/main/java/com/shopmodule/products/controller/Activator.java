package com.shopmodule.products.controller;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.BusFactory;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * Application begins from this class.
 *
 * @author AswiniN
 */

@Component(immediate = true)
public class Activator implements BundleActivator {

    private Server server;

    /**
     * Starts the bundle.
     *
     * @param context
     */
    public void start(BundleContext context) {
        System.out.println("Starting the bundle");
    }

    /**
     * Stops the bundle.
     *
     * @param context
     */
    public void stop(BundleContext context) {

        System.out.println("Stopping the bundle");
    }

    /**
     * Activates the server to implement REST services.
     */
    @Activate
    public void activate() {
        try {
            JAXRSServerFactoryBean bean = new JAXRSServerFactoryBean();
            bean.setAddress("/product");
            bean.setBus(BusFactory.getDefaultBus());
            bean.setProvider(new JacksonJsonProvider());
            bean.setServiceBean(new RestImpl());
            server = bean.create();
            System.out.println(server);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Deactivates the server.
     * @throws Exception
     */
    @Deactivate
    public void deactivate() throws Exception {
        if (server != null) {
            server.destroy();
        }
    }
}
