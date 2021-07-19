package com.example.ecommere.exception;

import com.example.ecommere.model.Category;

public class DataNotFoundException extends Exception{
    public DataNotFoundException(String message){
        super( message);
    }
}
