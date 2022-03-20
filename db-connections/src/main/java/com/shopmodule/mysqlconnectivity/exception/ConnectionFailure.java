package com.shopmodule.mysqlconnectivity.exception;

public class ConnectionFailure extends RuntimeException {
    
    public ConnectionFailure(String message) {
        super(message);
    } 

}
