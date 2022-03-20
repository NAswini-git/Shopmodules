package com.shopmodule.exception.userdefinedexceptions;

public class DatabaseAccessDeniedException extends CustomException{
    
    public DatabaseAccessDeniedException(String message) {
        super(message);
    } 

}
