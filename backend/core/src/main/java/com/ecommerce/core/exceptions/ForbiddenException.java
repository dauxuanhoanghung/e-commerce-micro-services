package com.ecommerce.core.exceptions;

/**
 * ForbiddenException class. Used to throw an exception when a forbidden action is attempted.
 */
public class ForbiddenException extends RuntimeException {

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a call to initCause.
     * <p>
     * Params: message – the detail message. The detail message is saved for later retrieval
     * by the {@link #getMessage()} method.
     */
    public ForbiddenException(String message) {
        super(message);
    }
}