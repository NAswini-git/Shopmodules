
package com.shopmodule.shop;

import com.shopmodule.authentication.view.LoginPortal;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Application begins from this class.
 *
 * @author AswiniN
 */
public class Activator implements BundleActivator {

    public static Bundle bundleid;

    /**
     * Starts the bundle.
     *
     * @param context
     */
    public void start(BundleContext context) {

        System.out.println("Starting the bundle");
        bundleid = context.getBundle();
        //LoginPortal.renderLoginPortal();
    }

    /**
     * Stops the bundle.
     *
     * @param context
     */
    public  void stop(BundleContext context) {

        System.out.println("Stopping the bundle");
    }
}