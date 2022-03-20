package com.shopmodule.products.exception;

public class UpdateFailedException extends RuntimeException {
    
    public UpdateFailedException(String message) {
        super(message);
    }
}
