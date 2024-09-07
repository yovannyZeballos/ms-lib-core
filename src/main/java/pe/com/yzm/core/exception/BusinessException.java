package pe.com.yzm.core.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * BusinessException is a custom exception class that extends Exception.
 * It includes additional fields for details and httpStatus.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BusinessException  extends Exception {
    private String message;
    private List<String> details;
    private HttpStatus httpStatus;

    /**
     * Static factory method for creating a new BusinessException.
     * If httpStatus is null, it defaults to HttpStatus.INTERNAL_SERVER_ERROR.
     *
     * @param message the error message
     * @param details the details of the error
     * @param httpStatus the HTTP status of the error
     * @return a new BusinessException with the provided details
     */
    public static BusinessException createException(String message,List<String> details,HttpStatus httpStatus) {
        return BusinessException.builder()
                .httpStatus(httpStatus == null ? HttpStatus.INTERNAL_SERVER_ERROR : httpStatus)
                .details(details)
                .message(message)
                .build();
    }
}