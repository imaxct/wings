package cn.sduonline.wings.exception.handler;

import cn.sduonline.wings.exception.AuthException;
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

    @ExceptionHandler({AuthException.class})
    public ResponseEntity<ErrorDetail> handleAuthException(AuthException exception, WebRequest request) {
        ErrorDetail detail = new ErrorDetail(false, exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(detail, HttpStatus.UNAUTHORIZED);
    }

}
