package me.com.lab2.model;

import me.com.lab2.R;

public class AllQuestions {
    private int questionIndex;
    private Question[] allQuestions = {
            new Question(R.string.q_start, true),
            new Question(R.string.q_1, false),
            new Question(R.string.q_2, false),
            new Question(R.string.q_3, false),
            new Question(R.string.q_4, true),
            new Question(R.string.q_5, true),
            new Question(R.string.q_6, true)
    };

    public int length(){
        return allQuestions.length;
    }
    public AllQuestions() {questionIndex = 0;}

    public Question getQuestion(int index) {
        index = index % allQuestions.length;
        return allQuestions[index];
    }
}
