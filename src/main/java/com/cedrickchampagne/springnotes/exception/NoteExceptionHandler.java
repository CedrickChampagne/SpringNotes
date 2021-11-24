package com.cedrickchampagne.springnotes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NoteExceptionHandler {

    /*Handle 404 note not found*/
    @ExceptionHandler
    public ResponseEntity<NoteErrorResponse> handleException(NoteNotFoundException e){
        NoteErrorResponse errorResponse = new NoteErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<NoteErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /*Handle bad requests*/
    @ExceptionHandler
    public ResponseEntity<NoteErrorResponse> handleException(Exception e){
        NoteErrorResponse errorResponse = new NoteErrorResponse();

        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<NoteErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
