package com.example.boat.exception;

public class RoleNotFoundException extends RuntimeException{

    public static final String DEFAULT_MESSAGE = "Error: Role not found.";

    public RoleNotFoundException(String message) {
        super(message);
    }

    public RoleNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
