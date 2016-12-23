package com.ipalma.rapreddit.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ipalma.rapreddit.R;
import com.ipalma.rapreddit.adapters.RedditsAdapter;
import com.ipalma.rapreddit.models.Reddit;
import com.ipalma.rapreddit.network.VolleySingleton;
import com.ipalma.rapreddit.services.RedditService;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
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
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void requestReddits() {
        RedditService.getReddits(TAG, new RedditService.RedditCallback() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onSuccess(List<Reddit> reddits) {
                recyclerView.setAdapter(new RedditsAdapter(MainActivity.this, reddits));
            }
        });
    }
}
