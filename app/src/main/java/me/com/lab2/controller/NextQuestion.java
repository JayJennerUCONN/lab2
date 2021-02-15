package me.com.lab2.controller;

import java.util.Random;

public class NextQuestion {
    private static int index = 0;
   //Random number = new Random();
   //final int rng = number.nextInt(6-1)+1;
    public int getCurrentQuestion() {return index;}
    public int getNextQuestionIndex(){
        index++;
        return index;
    }
}
