package com.edoe.api.services.exceptions;

import javax.servlet.ServletException;

public class NotCredentialException extends ServletException {
    private static final long serialVersionUID = 1L;

    public NotCredentialException(String msg) {
        super(msg);
    }

}
