package com.quizapi.model;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class QuizQuestionWithOptions {
    private String question;
    private List<String> options; 
    private String correctAnswer;

    public QuizQuestionWithOptions(TriviaQuestion trivia) {
        this.question = trivia.getQuestion();
        this.correctAnswer = trivia.getCorrect_answer();

        this.options = new ArrayList<>(trivia.getIncorrect_answers());
        this.options.add(trivia.getCorrect_answer());
        Collections.shuffle(this.options);
    }

    public String getQuestion() { return question; }
    public List<String> getOptions() { return options; }
    public String getCorrectAnswer() { return correctAnswer; }

private String questionId; 

public QuizQuestionWithOptions(TriviaQuestion trivia, String questionId) {
    this.question = trivia.getQuestion();
    this.correctAnswer = trivia.getCorrect_answer();
    this.options = new ArrayList<>(trivia.getIncorrect_answers());
    this.options.add(correctAnswer);
    Collections.shuffle(this.options);
    this.questionId = questionId;
}

public String getQuestionId() {
    return questionId;
}
}