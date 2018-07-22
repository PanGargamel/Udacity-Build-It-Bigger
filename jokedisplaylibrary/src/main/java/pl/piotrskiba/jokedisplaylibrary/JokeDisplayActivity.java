package pl.piotrskiba.jokedisplaylibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        Intent parentIntent = getIntent();
        if(parentIntent.hasExtra(Intent.EXTRA_TEXT)){
            TextView mJokeTextView = findViewById(R.id.tv_joke);
            mJokeTextView.setText(parentIntent.getStringExtra(Intent.EXTRA_TEXT));
        }
    }
}
