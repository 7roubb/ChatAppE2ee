package com.cotede.e2eechatapp.exceptions;

public class CustomExceptions {
    public static class UserNotFound extends RuntimeException {
        public UserNotFound(String UserName) {
            super(UserName);
        }
    }
}
