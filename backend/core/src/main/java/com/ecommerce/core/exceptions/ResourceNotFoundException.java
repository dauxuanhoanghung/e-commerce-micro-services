package com.ecommerce.core.exceptions;

/**
 * ResourceNotFoundException class. Used to throw an exception when a resource is not found.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a call to initCause.
     * <p>
     * Params: message – the detail message. The detail message is saved for later retrieval
     * by the {@link #getMessage()} method.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

