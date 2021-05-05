package org.js.azdanov.api.users.exception;

public class UsersServiceException extends RuntimeException {
    private static final long serialVersionUID = 1232293714708468252L;

    public UsersServiceException() {
        super();
    }

    public UsersServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsersServiceException(String message) {
        super(message);
    }

    public UsersServiceException(Throwable cause) {
        super(cause);
    }
}
