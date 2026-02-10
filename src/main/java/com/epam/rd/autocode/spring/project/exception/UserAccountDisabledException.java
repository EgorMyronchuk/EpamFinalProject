package com.epam.rd.autocode.spring.project.exception;

public class UserAccountDisabledException extends RuntimeException{

    public UserAccountDisabledException(String message) {
        super(message);
    }
}
