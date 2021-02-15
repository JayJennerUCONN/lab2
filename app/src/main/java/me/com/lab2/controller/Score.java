package me.com.lab2.controller;
import me.com.lab2.model.AllQuestions;
public class Score {
    public final int CORRECT_ANSWER = 20;
    public final int SKIP_QUESTION = -5;
    public int score;
    public int streak;

    public Score(){
            score = 0;
            streak = 0;
    }

    AllQuestions allQs = new AllQuestions();
    public void correctAnswer() {
        score += CORRECT_ANSWER;
        streak++;
    }

    public void wrongAnswer(){
        streak = 0;
    }

    public void skipQuestion() {
        score += SKIP_QUESTION;
        streak = 0;
    }
    public int getScore() {return score;}

    public int getStreak() {return streak;}
}
