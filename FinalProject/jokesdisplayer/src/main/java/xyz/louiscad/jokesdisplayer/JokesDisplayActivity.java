package xyz.louiscad.jokesdisplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class JokesDisplayActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView contentTextView = (TextView) findViewById(R.id.contentText);
        String jokeText = getIntent().getStringExtra(EXTRA_JOKE);
        contentTextView.setText(jokeText);
    }
}
