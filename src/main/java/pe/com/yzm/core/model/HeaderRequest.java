package pe.com.yzm.core.model;

import lombok.*;

/**
 * HeaderRequest is a model class that represents a request header.
 * It includes a field for transactionId.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class HeaderRequest {
    /**
     * The transaction ID of the request.
     */
    private String transactionId;
}