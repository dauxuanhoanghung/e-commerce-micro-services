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
import java.util.List;

/**
 * This class is responsible for creating a pagination response object.
 * @param <T>
 */
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaginationResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;

    /**
     * The size of the data.
     */
    Integer size;
    /**
     * The current page.
     */
    Integer currentPage;
    /**
     * The total pages.
     */
    Integer totalPages;
    /**
     * The data response.
     */
    List<T> data;
}
