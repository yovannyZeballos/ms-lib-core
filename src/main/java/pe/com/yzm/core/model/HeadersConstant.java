package pe.com.yzm.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * HeadersConstant is an interface that contains constants for HTTP headers.
 * It includes an enum HeaderRequest that represents the request headers.
 */
public interface  HeadersConstant {

    /**
     * HeaderRequest is an enum that represents the request headers.
     * It includes a field for transactionId.
     */
    @Getter
    @AllArgsConstructor
    enum HeaderRequest {
        /**
         * The transaction ID of the request.
         */
        TRANSACTION_ID("Transaction-Id");
        private final String key;
    }
}