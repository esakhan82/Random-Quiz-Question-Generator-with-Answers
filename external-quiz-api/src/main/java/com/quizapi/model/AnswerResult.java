package com.quizapi.model;

public class AnswerResult {
    private boolean correct;
    private String message;

    public AnswerResult(boolean correct, String message) {
        this.correct = correct;
        this.message = message;
    }

    public boolean isCorrect() { return correct; }
    public String getMessage() { return message; }
}
