package com.example.wipro.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> claimNotFoundHandler(UserNotFoundException exception) {
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<String> claimExistsHandler(UserExistsException exception) {
        return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(QuestionExistsException.class)
    public ResponseEntity<String> questionExistsHandler(QuestionExistsException exception) {
        return new ResponseEntity<>("Question doesn't exists",HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AnswerExistsException.class)
    public ResponseEntity<String> answerExistsHandler(AnswerExistsException exception) {
        return new ResponseEntity<>("Answer doesn't exists",HttpStatus.CONFLICT);
    }


}