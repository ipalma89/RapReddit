package com.ipalma.rapreddit.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ipalma.rapreddit.R;
import com.ipalma.rapreddit.adapters.RedditsAdapter;
import com.ipalma.rapreddit.models.Reddit;
import com.ipalma.rapreddit.network.VolleySingleton;
import com.ipalma.rapreddit.services.RedditService;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private View emptyView;
    private TextView emptyViewText;
    private Button retryBtn;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        requestReddits();
    }

    @Override
    protected void onStop() {
        super.onStop();
        VolleySingleton.getInstance().cancelRequestsFor(TAG);
    }

    private void initializeViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        emptyView = findViewById(R.id.empty_view);
        emptyViewText = (TextView) findViewById(R.id.empty_view_text);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        retryBtn = (Button) findViewById(R.id.retry_btn);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestReddits();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void requestReddits() {
        setLoading(true);

        RedditService.getReddits(TAG, new RedditService.RedditCallback() {
            @Override
            public void onError(String message) {
                setLoading(false);
                recyclerView.setVisibility(View.GONE);
                emptyViewText.setText(message);
            }

            @Override
            public void onSuccess(List<Reddit> reddits) {
                setLoading(false);

                if (reddits.size() > 0) {
                    emptyView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setAdapter(new RedditsAdapter(MainActivity.this, reddits));
                } else {
                    recyclerView.setVisibility(View.GONE);
                    emptyViewText.setText(R.string.empty_list);
                }
            }
        });
    }

    private void setLoading(boolean isLoading) {
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        emptyView.setVisibility(isLoading ? View.GONE : View.VISIBLE);
    }
}
