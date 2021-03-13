package com.emarsys.exception;

public class TimeValidationException extends RuntimeException {

    private static final long serialVersionUID = -2125454850861864549L;

    public TimeValidationException(final String errorMessage) {
        super(errorMessage);
    }
}
