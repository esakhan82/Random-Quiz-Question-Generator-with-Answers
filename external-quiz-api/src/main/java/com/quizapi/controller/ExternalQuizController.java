package com.quizapi.controller;

import com.quizapi.model.TriviaQuestion;
import com.quizapi.model.TriviaResponse;
import com.quizapi.model.QuizQuestionWithOptions;
import com.quizapi.model.AnswerRequest;
import com.quizapi.model.AnswerResult;
import com.quizapi.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/external-quiz")
public class ExternalQuizController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private QuizService quizService;

    @GetMapping("/quiz")
    public List<QuizQuestionWithOptions> getQuizQuestions(@RequestParam(defaultValue = "5") int count) {
        // String url = "https://opentdb.com/api.php?amount=" + count + "&category=18&type=multiple";
        String url = "https://opentdb.com/api.php?amount=" + count + "&category=18&type=multiple";

        TriviaResponse response = restTemplate.getForObject(url, TriviaResponse.class);

        if (response == null || response.getResults() == null) return List.of();

        return response.getResults().stream()
                .map(q -> {
                    String id = quizService.registerCorrectAnswer(q.getQuestion(), q.getCorrect_answer());
                    return new QuizQuestionWithOptions(q, id);
                })
                .toList();
    }

    @PostMapping("/submit")
    public AnswerResult checkAnswer(@RequestBody AnswerRequest answerRequest) {
        String correct = quizService.getCorrectAnswer(answerRequest.getQuestionId());

        if (correct == null) {
            return new AnswerResult(false, "⚠️ Question not recognized or expired.");
        }

        if (answerRequest.getSelectedAnswer().equals(correct)) {
            return new AnswerResult(true, "✅ Correct Answer!");
        } else {
            return new AnswerResult(false, "❌ Wrong Answer. Correct is: " + correct);
        }
    }
}
