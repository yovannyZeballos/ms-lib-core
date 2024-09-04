package pe.com.yzm.core.logger;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.com.yzm.core.constant.ConstantMessage;

/**
 * Utility class for logging input and output data.
 * This class is not meant to be instantiated, hence the private constructor.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoggerUtil {

    /**
     * Logs the input data.
     *
     * @param transactionId the transaction ID
     * @param headers       the headers
     * @param body          the body of the input
     */
    public static void logInput(String transactionId, String headers, Object body) {
        String nameClass = Thread.currentThread().getStackTrace()[2].getClassName();
        Integer lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
        log.info(String.format(ConstantMessage.LOG_PARAMS_IN_HEADERS_BODY, nameClass, lineNumber, transactionId, headers, body));
    }

    /**
     * Logs the output data.
     *
     * @param transactionId the transaction ID
     * @param headers       the headers
     * @param body          the body of the output
     */
    public static void logOutput(String transactionId, String headers, String body) {
        String nameClass = Thread.currentThread().getStackTrace()[2].getClassName();
        Integer lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
        log.info(String.format(ConstantMessage.LOG_PARAMS_OUT_HEADERS_BODY, nameClass, lineNumber, transactionId, headers, body));
    }

    /**
     * Logs an error.
     *
     * @param transactionId the transaction ID
     * @param error         the error
     */
    public static void logError(String transactionId, Throwable error) {
        String nameClass = Thread.currentThread().getStackTrace()[2].getClassName();
        Integer lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
        log.error(String.format(ConstantMessage.LOG_ERROR, nameClass, lineNumber, transactionId, error));
    }

}