// src/main/java/com/example/demo/exception/ResourceNotFoundException.java
package com.example.demo.exception;

public class RequestNotFoundException extends RuntimeException {
    public RequestNotFoundException(String message) { super(message); }
}
