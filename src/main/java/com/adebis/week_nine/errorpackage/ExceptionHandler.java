package com.adebis.week_nine.errorpackage;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomError.class)
    public ResponseEntity<ErrorMessenger> errorHandler(CustomError e, WebRequest webRequest){
        ErrorMessenger errorMessenger = new ErrorMessenger( e.status, e.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorMessenger, e.status);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessenger> handleGeneralError(Exception e, WebRequest webRequest){
        ErrorMessenger errorMessenger = ErrorMessenger.builder()
                .Status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .details(webRequest.getDescription(false))
                .build();

        return new ResponseEntity<>(errorMessenger, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
