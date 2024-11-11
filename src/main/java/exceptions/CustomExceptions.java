package exceptions;

public class CustomExceptions {
    public static class UserNotFound extends RuntimeException {
        public UserNotFound(String message) {
            super(message);
        }
    }
}
