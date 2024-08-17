package pe.com.yzm.core.component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import pe.com.yzm.core.constant.ConstantMessage;
import pe.com.yzm.core.model.ErrorResponse;
import pe.com.yzm.core.model.HeadersConstant;
import pe.com.yzm.core.model.Wrapper;
import reactor.core.publisher.Mono;

/**
 * CustomWebFilter is a WebFilter implementation that validates the request headers.
 * If the header validation fails, it returns a HTTP 400 status code with an error response.
 */
@Slf4j
@Component
public class CustomWebFilter implements WebFilter {

    @Value("${header.no-control.active:true}")
    private boolean isHeaderNoControlActive;

    /**
     * This method filters incoming HTTP requests.
     * It validates the request headers and if the validation fails, it returns a HTTP 400 status code with an error response.
     * If the validation passes, it forwards the request to the next filter in the chain.
     *
     * @param exchange the current server exchange
     * @param chain provides a way to delegate to the next filter in the chain
     * @return Mono<Void> indicating when request handling is complete
     */
    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.info("--- CustomWebFilter:filter ---");
        logRequest(exchange.getRequest());

        if (isHeaderNoControlActive) {
            return chain.filter(exchange);
        }

        HttpHeaders httpHeaders = exchange.getRequest().getHeaders();

        boolean isRequestHeaderValid = validateRequest(httpHeaders);
        if (isRequestHeaderValid) {
            log.info(ConstantMessage.HEADERS_VALID_MSG);
        } else {
            log.warn(ConstantMessage.HEADERS_INVALID_MSG);
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .message(ConstantMessage.HEADERS_INVALID_MSG)
                    .details(Collections.singletonList(ConstantMessage.HEADERS_REQUIRED_TRANSACTION_ID))
                    .build();
            DataBuffer buffer = response
                    .bufferFactory()
                    .wrap(new ObjectMapper().writeValueAsBytes(Wrapper.builder()
                                    .data(errorResponse)
                            .build()));
            return response.writeWith(Mono.just(buffer));
        }
        return chain.filter(exchange);
    }

    /**
     * This method logs the details of the incoming HTTP request.
     *
     * @param request the incoming HTTP request
     */
    private void logRequest(ServerHttpRequest request) {
        log.info("Request: method={}, url={}", request.getMethod(), request.getURI());
        request.getHeaders().forEach((name, values) -> logHeader("Header Request", name, values));
    }

    /**
     * This method logs the details of a specific HTTP header.
     *
     * @param message the log message
     * @param name the name of the header
     * @param values the values of the header
     */
    private void logHeader(String message, String name, List<String> values) {
        values.forEach(value -> log.info("{}: {}={}", message, name, value));
    }

    /**
     * This method validates the request headers.
     * It checks if the headers contain a specific key (TRANSACTION_ID).
     *
     * @param headers the HTTP headers of the request
     * @return true if the headers contain the key, false otherwise
     */
    private boolean validateRequest(HttpHeaders headers) {
        Map<String, String> result = headers.entrySet().stream()
                .filter(m -> (m.getKey().equalsIgnoreCase(HeadersConstant.HeaderRequest.TRANSACTION_ID.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, m -> m.getValue().get(0)));
        return (result.size() == 1);
    }

}