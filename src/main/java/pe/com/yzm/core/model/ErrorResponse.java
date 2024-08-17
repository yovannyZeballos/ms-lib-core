package pe.com.yzm.core.model;

import lombok.*;

import java.util.List;

/**
 * ErrorResponse is a model class that represents an error response.
 * It includes fields for message and details.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ErrorResponse {
    /**
     * The error message.
     */
    private String message;

    /**
     * The details of the error.
     */
    private List<String> details;
}