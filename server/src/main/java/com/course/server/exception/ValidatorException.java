package com.course.server.exception;

/**
 * @author EaApple
 * @6/7/2020 9:24 AM
 * description：
 */
public class ValidatorException extends RuntimeException{

    public ValidatorException(String message){
        super(message);
    }
}
