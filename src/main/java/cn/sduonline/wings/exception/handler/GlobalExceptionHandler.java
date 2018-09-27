package cn.sduonline.wings.exception.handler;

import cn.sduonline.wings.exception.AuthException;
import cn.sduonline.wings.exception.ServiceException;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by imaxct on 18-9-27.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String LOG_NAME = "wings-service";

    private static final Logger LOGGER = LoggerFactory.getLogger(LOG_NAME);

    @ExceptionHandler({AuthException.class, AuthenticationException.class})
    public ResponseEntity<ErrorDetail> handleAuthException(Exception exception, WebRequest request) {
        ErrorDetail detail = new ErrorDetail(false, exception.getMessage(), request.getDescription(false));
        LOGGER.info("handleAuthException", exception);
        return new ResponseEntity<>(detail, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorDetail> handleIllegalArgumentException(IllegalArgumentException exception, WebRequest request) {
        ErrorDetail detail = new ErrorDetail(false, exception.getMessage(), request.getDescription(false));
        LOGGER.info("handleIllegalArgumentException", exception);
        return new ResponseEntity<>(detail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<ErrorDetail> handleServiceException(ServiceException exception, WebRequest request) {
        ErrorDetail detail = new ErrorDetail(false, exception.getMessage(), request.getDescription(false));
        LOGGER.info("handleServiceException", exception);
        return new ResponseEntity<>(detail, HttpStatus.OK);
    }
}
