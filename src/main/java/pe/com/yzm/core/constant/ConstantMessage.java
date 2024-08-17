package pe.com.yzm.core.constant;

import lombok.experimental.UtilityClass;

/**
 * This class contains constant messages that are used throughout the application.
 * It is marked with @UtilityClass which makes the class final and its constructor private.
 */
@UtilityClass
public class ConstantMessage {

    /**
     * Message indicating that the request headers are invalid.
     */
    public static final String HEADERS_INVALID_MSG = "Request headers invalid";

    /**
     * Message indicating that the request headers are valid.
     */
    public static final String HEADERS_VALID_MSG = "Request headers valid.";

    /**
     * Message indicating that the Transaction-Id header is required.
     */
    public static final String HEADERS_REQUIRED_TRANSACTION_ID = "Transaction-Id is required";

    /**
     * Format string for logging the input data.
     * It includes placeholders for the class name, line number, transaction ID, headers, and body.
     */
    public static final String LOG_PARAMS_IN_HEADERS_BODY = "%s : %d - [idTx=%s] Datos de entrada request: Headers=%s Body=%s";

    /**
     * Format string for logging the output data.
     * It includes placeholders for the class name, line number, transaction ID, application ID, headers, and body.
     */
    public static final String LOG_PARAMS_OUT_HEADERS_BODY = "%s : %d - [idTx=%s] Datos de salida response: Headers=%s Body=%s";
}