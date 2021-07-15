package com.example.ecommere.exception;

import com.example.ecommere.model.Category;

public class CategoryException extends RuntimeException{
    public CategoryException(Long Id) {
        super("Could not get category with id = " + Id);
    }

    public CategoryException(Category category) {
        super("Could not save category");
    }
}
