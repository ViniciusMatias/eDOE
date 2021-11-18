package com.edoe.api.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDate;


@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ExpiredJwtException.class})
    public ResponseEntity<Object> handlerException(Exception e, WebRequest request)
    {

        Error error = new Error();
        error.setTimestamp(LocalDate.now());
        error.setError(e.getCause());
        error.setMessage(e.getMessage());

        return  new ResponseEntity<Object>(error, HttpStatus.FORBIDDEN);
    }

}
