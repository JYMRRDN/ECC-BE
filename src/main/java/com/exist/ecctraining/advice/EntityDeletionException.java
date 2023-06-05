package com.exist.ecctraining.advice;

public class EntityDeletionException extends RuntimeException{
    public EntityDeletionException(String message) {
        super(message);
    }
}
