package store.book.auth.exceptions;

public class PasswordsNotMatchingException extends RuntimeException {
    public PasswordsNotMatchingException(String message) {
        super(message);
    }
}
