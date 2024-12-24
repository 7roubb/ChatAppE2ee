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

    public static class FriendAlreadyExistsException extends RuntimeException {
        public FriendAlreadyExistsException(String UserName) {
            super(UserName);
        }
    }

    public static class FriendNotFoundException extends RuntimeException {
        public FriendNotFoundException(String UserName) {
            super(UserName);
        }
    }
    public static class RequestNotFoundException extends RuntimeException {
        public RequestNotFoundException(String UserName) {
            super(UserName);
        }
    }
}