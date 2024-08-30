package com.ecommerce.core.dto.responses;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

/**
 * This class is responsible for creating a response object
 * that will be returned to the client and among services.
 *
 * @param <T>
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    /**
     * The response code. Define as HTTP status code.
     */
    Integer code;

    /**
     * The response message.
     */
    String message;

    /**
     * The response timestamp. Define as the current time in milliseconds.
     */
    Long timestamp = Instant.now().toEpochMilli();

    /**
     * The response data. It can be any object.
     */
    T data;

    /**
     * Constructor to create a response object.
     *
     * @param code    HTTP status code
     * @param message Response message
     * @param data    Response data
     */
    @Builder
    public ApiResponse(final Integer code, final String message, final T data) {
        this.code = code;
        this.message = message;
        this.timestamp = Instant.now().toEpochMilli();
        this.data = data;
    }
}
