package com.nashss.se.WrenchWench.exceptions;

public class VehicleNotFoundException extends RuntimeException {
    private static final long serialVersionUID =  -5369918167575968618L;

    //Exception with no message or cause.
    public VehicleNotFoundException() {
        super();
    }

    // Exception with message and no cause
    public VehicleNotFoundException(String message){
        super(message);
    }

    // Exception with a message and cause
    public VehicleNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    // Exception with a cause, but no message
    public VehicleNotFoundException(Throwable cause){
        super(cause);
    }

}
