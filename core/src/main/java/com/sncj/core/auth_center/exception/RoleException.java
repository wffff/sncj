package com.sncj.core.auth_center.exception;

public class RoleException extends CoreException {

    public RoleException() {
    }

    public RoleException(String message) {
        super(message);
    }

    public RoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleException(Throwable cause) {
        super(cause);
    }

    public RoleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
