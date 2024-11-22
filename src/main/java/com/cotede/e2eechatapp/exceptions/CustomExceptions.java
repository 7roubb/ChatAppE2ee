package com.cotede.e2eechatapp.exceptions;

public class CustomExceptions {
    public static class UserNotFound extends RuntimeException {
        public UserNotFound(String UserName) {
            super(UserName);
        }
    }
    public static class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String UserName) {
            super(UserName);
        }
    }
    public static class EmailAlreadyExistsException extends RuntimeException {
        public EmailAlreadyExistsException(String UserName) {
            super(UserName);
        }
    }
}