package com.quizapi.model;

import java.util.List;

public class TriviaResponse {
    private int response_code;
    private List<TriviaQuestion> results;

    public int getResponse_code() { return response_code; }
    public void setResponse_code(int response_code) { this.response_code = response_code; }
    public List<TriviaQuestion> getResults() { return results; }
    public void setResults(List<TriviaQuestion> results) { this.results = results; }
}
