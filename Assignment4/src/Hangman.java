/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.util.Arrays;

public class Hangman extends ConsoleProgram {

    private HangmanCanvas canvas = new HangmanCanvas();

    public void init() {
        add(canvas);
        canvas.reset();
    }

    public void run() {
		/* You fill this in */
        HangmanLexicon lexicon = new HangmanLexicon();
        RandomGenerator rgen = new RandomGenerator();

        String word = lexicon.getWordFromFile("ShorterLexicon.txt");
        word=word.toUpperCase();

        StringBuilder word_to_guess = new StringBuilder(word.replaceAll(".", "-"));

        int word_len = lexicon.getWordCount(word);
        int count = 0;
        int life = 8;

        println("Welcome to Hangman! Hahaha...");
        //println("The answer is: "+word);
        while (count<word_len && life>0) {

            println("The word now looks like this: "+ word_to_guess);

            canvas.displayWord(word_to_guess.toString());

            if (life>1) {
                println("You have "+life+" guesses left.");
            }
            else {
                println("You only have one guess left.");
            }

            String str_input = readLine("Your guess: ");

            if (str_input.length()!=1) {
                println("Please input one character each time.");
                life -= 1;
                canvas.noteIncorrectGuess(str_input);
                continue;
            }

            str_input=str_input.toUpperCase();

            if (word.contains(str_input) && !word_to_guess.toString().contains(str_input)) {
                println("The guess is correct");

                int current_idx = 0;
                while (current_idx<word_len && word.indexOf(str_input, current_idx)!=-1) {
                    current_idx = word.indexOf(str_input, current_idx);
                    word_to_guess.setCharAt(current_idx, word.charAt(current_idx));
                    current_idx+=1;
                    count += 1;
                }
            }
            else if (word_to_guess.toString().contains(str_input)) {
                println("Please input another character.");
                life -= 1;
                canvas.noteIncorrectGuess(str_input);
            }
            else {
                println("There are no "+str_input+"'s in the word.");
                life -= 1;
                canvas.noteIncorrectGuess(str_input);
            }
        }

        if (count>=word_len && life>=0) {
            println("You win!");
        }
        else {
            println("You lose!");
        }
	}

}
