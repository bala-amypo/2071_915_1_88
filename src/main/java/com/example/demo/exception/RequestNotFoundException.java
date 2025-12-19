// src/main/java/com/example/demo/exception/ResourceNotFoundException.java
package com.example.demo.exception;

public class RequestNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) { super(message); }
}
