package com.ipalma.rapreddit.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ipalma.rapreddit.R;
import com.ipalma.rapreddit.models.Reddit;

public class RedditDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reddit_details);

        Reddit reddit = (Reddit) getIntent().getParcelableExtra("reddit");
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(reddit.getUrl());
    }
}
