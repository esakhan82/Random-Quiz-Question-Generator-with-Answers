package com.quizapi.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class QuizService {

    private final Map<String, String> answerMap = new HashMap<>();

    public String registerCorrectAnswer(String question, String correctAnswer) {
        String id = UUID.randomUUID().toString();
        answerMap.put(id, correctAnswer);
        return id;
    }

    public String getCorrectAnswer(String questionId) {
        return answerMap.get(questionId);
    }
}
