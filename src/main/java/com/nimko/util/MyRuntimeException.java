package com.nimko.util;

public class MyRuntimeException extends RuntimeException{

    public MyRuntimeException(Exception e) {
        super(e);
    }
}
