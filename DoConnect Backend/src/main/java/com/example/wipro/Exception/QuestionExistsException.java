package com.example.wipro.Exception;

public class QuestionExistsException extends Exception {
    public QuestionExistsException() {
        super();
    }

    public QuestionExistsException(String message) {
        super(message);
    }
}
