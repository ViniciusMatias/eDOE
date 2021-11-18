package com.edoe.api.exceptions;

import org.springframework.http.HttpStatus;
import java.time.LocalDate;

public class Error {

    private LocalDate timestamp;
    private HttpStatus status;
    private Throwable error;
    private String message;

    public Error(LocalDate timestamp, HttpStatus status, Throwable error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public Error() {
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
