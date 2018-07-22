package pl.piotrskiba.jokeslib;

import java.util.Random;

public class JokeProvider {

    private String[] mJokes;
    private Random mRandom;

    public JokeProvider(){
        mJokes = new String[4];
        mRandom = new Random();

        mJokes[0] = "How many programmers does it take to screw in a light bulb?\nNone. It's a hardware problem.";
        mJokes[1] = "Why C gets all the chicks and Java doesn't? Because C doesn't treat them like objects.";
        mJokes[2] = "In order to understand recursion you must first understand recursion.";
        mJokes[3] = "Today's News: Developer accused of unreachable code refuses to comment.";
    }

    public String getRandomJoke(){
        return mJokes[mRandom.nextInt(mJokes.length)];
    }
}
