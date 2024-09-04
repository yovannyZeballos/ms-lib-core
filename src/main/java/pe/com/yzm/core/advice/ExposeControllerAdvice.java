package pe.com.yzm.core.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import pe.com.yzm.core.constant.ConstantMessage;
import pe.com.yzm.core.exception.BusinessException;
import pe.com.yzm.core.model.ErrorResponse;
import pe.com.yzm.core.model.Wrapper;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExposeControllerAdvice {

  @ExceptionHandler(value = {BusinessException.class})
  public ResponseEntity<Wrapper<ErrorResponse>> handleBusinessException(BusinessException exception) {
    final var errorResponse = ErrorResponse
        .builder()
        .message(exception.getMessage())
        .details(exception.getDetails())
        .build();

    return new ResponseEntity<>(Wrapper.<ErrorResponse>builder()
        .data(errorResponse)
        .build(), exception.getHttpStatus());
  }

  @ExceptionHandler(value = {WebExchangeBindException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Wrapper<ErrorResponse> handleWebExchangeBindException(WebExchangeBindException exception) {

    final var errorResponse = ErrorResponse
        .builder()
        .message(exception.getReason())
        .details(exception.getFieldErrors().stream()
            .map(fieldError -> String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage()))
            .collect(Collectors.toList()))
        .build();

    return Wrapper.<ErrorResponse>builder()
        .data(errorResponse)
        .build();
  }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Wrapper<ErrorResponse> handleException(Exception exception) {

        final var errorResponse = ErrorResponse
                .builder()
                .message(ConstantMessage.GENERIC_MESSAGE)
                .details(List.of(exception.getMessage()))
                .build();

        return Wrapper.<ErrorResponse>builder()
                .data(errorResponse)
                .build();
    }

}
