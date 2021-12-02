package com.edoe.api.exceptions;

import com.edoe.api.services.exceptions.NotCredentialException;
import com.edoe.api.services.exceptions.RepeatedNameException;
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


    @ExceptionHandler({RepeatedNameException.class})
    public ResponseEntity<Object> RepeatedNameException(Exception e, WebRequest request)
    {

        Error error = new Error();
        error.setStatus(HttpStatus.NOT_FOUND);
        error.setTimestamp(LocalDate.now());
        error.setMessage(e.getMessage());

        return  new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ExpiredJwtException.class})
    public ResponseEntity<Object> NotCredentialException(Exception e, WebRequest request)
    {

        Error error = new Error();
        error.setStatus(HttpStatus.FORBIDDEN);
        error.setTimestamp(LocalDate.now());
        error.setMessage(e.getMessage());

        return  new ResponseEntity<Object>(error, HttpStatus.FORBIDDEN);
    }

}
