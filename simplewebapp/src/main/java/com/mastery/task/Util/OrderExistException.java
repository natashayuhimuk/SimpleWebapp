package com.mastery.task.Util;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class OrderExistException extends RuntimeException{
    public OrderExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
