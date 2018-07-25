package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import pl.piotrskiba.jokedisplaylibrary.JokeDisplayActivity;
import pl.piotrskiba.jokeslibrary.JokeProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static Toast mToast;
    public void tellJoke(View view) {
        /*JokeProvider jokeProvider = new JokeProvider();
        String joke = jokeProvider.getRandomJoke();

        Intent intent = new Intent(this, JokeDisplayActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, joke);
        startActivity(intent);*/

        new EndpointsAsyncTask(this).execute();
    }


}
