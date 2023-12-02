package com.example.quizapp;

public class QuizResult {

    private int selectedQuestions;
    private int correctAnswers;

    private int attempts;

    public int getSelectedQuestions() {
        return selectedQuestions;
    }

    public void setSelectedQuestions(int selectedQuestions) {
        this.selectedQuestions = selectedQuestions;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    @Override
    public String toString() {
        return "QuizResult{" +
                "selectedQuestions=" + selectedQuestions +
                ", correctAnswers=" + correctAnswers +
                ", attempts=" + attempts +
                '}';
    }
}
