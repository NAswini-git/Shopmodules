package com.shopmodule.shop;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.shopmodule.authentication.view.LoginPortal;
import com.shopmodule.products.controller.RestImpl;

import com.shopmodule.products.view.ProductManager;
import org.apache.cxf.BusFactory;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.log4j.Logger;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * Server lifecycle execution.
 *
 * @author AswiniN
 */
@Component(immediate = true)
public class RestActivator  {

    private Server server;
    private static final Logger LOGGER = Logger.getLogger(RestActivator.class);

    /**
     * Activates the server to implement REST services.
     */
    @Activate
    public void activate() {

        try {
            final JAXRSServerFactoryBean bean = new JAXRSServerFactoryBean();
            bean.setAddress("/product");
            bean.setBus(BusFactory.getDefaultBus());
            bean.setProvider(new JacksonJsonProvider());
            bean.setServiceBean(new RestImpl());
            server = bean.create();
          //  LoginPortal.renderLoginPortal();

        } catch (Exception e) {
            LOGGER.info(e);
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