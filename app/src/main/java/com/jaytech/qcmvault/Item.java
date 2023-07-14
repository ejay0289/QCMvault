package com.jaytech.qcmvault;

import java.util.List;

public class Item {
    String question;
    List<String> answerOptions;
    List<Boolean> correctAnswers;

    public Item(String question, List<String> answerOptions, List<Boolean> correctAnswers) {
        this.question = question;
        this.answerOptions = answerOptions;
        this.correctAnswers = correctAnswers;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setAnswerOptions(List<String> answerOptions) {
        this.answerOptions = answerOptions;
    }

    public List<String> getAnswerOptions() {
        return answerOptions;
    }

    public void setCorrectAnswers(List<Boolean> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public List<Boolean> getCorrectAnswers() {
        return correctAnswers;
    }
}

