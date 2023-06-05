package com.nashss.se.WrenchWench.exceptions;

public class InvalidVinException extends RuntimeException{
    private static final long serialVersionUID =  -5369918167575968618L;

    //Exception with no message or cause.
    public InvalidVinException() {
        super();
    }

    // Exception with message and no cause
    public InvalidVinException(String message){
        super(message);
    }

    // Exception with a message and cause
    public InvalidVinException(String message, Throwable cause){
        super(message, cause);
    }

    // Exception with a cause, but no message
    public InvalidVinException(Throwable cause){
        super(cause);
    }

}
