package com.aaryadewa.rnd.simpanpinjam.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

public final class PaginationUtil {

  private static final String HEADER_X_TOTAL_COUNT = "X-Total-Count";

  private PaginationUtil() {
  }

  /**
     * Generate pagination headers for a Spring Data {@link org.springframework.data.domain.Page} object.
     *
     * @param uriBuilder The URI builder.
     * @param page The page.
     * @param <T> The type of object.
     * @return http header.
     */
    public static <T> HttpHeaders generatePaginationHttpHeaders(Page<T> page) {
      HttpHeaders headers = new HttpHeaders();
      headers.add(HEADER_X_TOTAL_COUNT, Long.toString(page.getTotalElements()));
      return headers;
  }
}
