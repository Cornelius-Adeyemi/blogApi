package com.adebis.week_nine.errorpackage;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class CustomError extends  Exception {
    public HttpStatus status;

    public HttpStatusCode code;

    public CustomError(String message, HttpStatus httpStatus,HttpStatusCode code){
        super(message);
        status = httpStatus;
        this.code = code;
    }

}
