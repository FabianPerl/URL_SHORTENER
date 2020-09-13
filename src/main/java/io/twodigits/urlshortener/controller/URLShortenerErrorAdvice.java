package io.twodigits.urlshortener.controller;

import io.twodigits.urlshortener.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class URLShortenerErrorAdvice
{
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler({URLShortenerException.class})
    public ResponseEntity<String> handleURLShortenerException(URLShortenerException e) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler({RedirectionException.class})
    public ResponseEntity<String> handleRedirectionException(RedirectionException e) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler({URLExistsException.class})
    public ResponseEntity<String> handleURLExistsException(URLExistsException e) {
        return error(HttpStatus.CONFLICT, e);
    }

    @ExceptionHandler({URLNotFoundException.class})
    public ResponseEntity<String> handleURLNotFoundException(URLNotFoundException e) {
        return error(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler({URLNotValidException.class})
    public ResponseEntity<String> handleURLNotValidException(URLNotValidException e) {
        return error(HttpStatus.BAD_REQUEST, e);
    }

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
