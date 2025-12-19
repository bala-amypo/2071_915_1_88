// src/main/java/com/example/demo/exception/BadRequestException.java
package com.example.demo.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) { super(message); }
}
