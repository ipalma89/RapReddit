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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String KEY_PARCEL_REDDITS = "reddits_parcel_key";

    private View emptyView;
    private TextView emptyViewText;
    private Button retryBtn;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RedditsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        if (adapter == null) {
            requestReddits();
        } else {
            setLoading(false);
        }
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
                emptyViewText.setText(message);
                setLoading(false);
            }

            @Override
            public void onSuccess(ArrayList<Reddit> reddits) {
                if (reddits.size() > 0) {
                    adapter = new RedditsAdapter(MainActivity.this, reddits);
                    recyclerView.setAdapter(adapter);
                } else {
                    emptyViewText.setText(R.string.empty_list);
                }
                setLoading(false);
            }
        });
    }

    private void setLoading(boolean isLoading) {
        // set loading state
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);

        // also check the content views state
        boolean haveItems = adapter != null;
        emptyView.setVisibility(isLoading || haveItems ? View.GONE : View.VISIBLE);
        recyclerView.setVisibility(isLoading || !haveItems ? View.GONE : View.VISIBLE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // save state of items list so we don't request them again when screen is rotated
        if (adapter != null) {
            outState.putParcelableArrayList(KEY_PARCEL_REDDITS, adapter.getReddits());
        }

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // restore saved items
        ArrayList<Reddit> reddits = savedInstanceState.getParcelableArrayList(KEY_PARCEL_REDDITS);
        if (reddits != null) {
            adapter = new RedditsAdapter(MainActivity.this, reddits);
            recyclerView.setAdapter(adapter);
        }

        super.onRestoreInstanceState(savedInstanceState);
    }
}
