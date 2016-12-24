package com.ipalma.rapreddit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ipalma.rapreddit.R;
import com.ipalma.rapreddit.models.Reddit;
import com.ipalma.rapreddit.viewholders.RedditViewHolder;

import java.util.ArrayList;

/**
 * Adapter for the reddits recycler view
 * Created by ivan on 21/12/2016.
 */

public class RedditsAdapter extends RecyclerView.Adapter<RedditViewHolder> {

    private final Context context;
    private final ArrayList<Reddit> reddits;

    public RedditsAdapter(Context context, ArrayList<Reddit> reddits) {
        this.context = context;
        this.reddits = reddits;
    }

    @Override
    public RedditViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_reddits, parent, false);

        return new RedditViewHolder(context, itemView);
    }

    @Override
    public void onBindViewHolder(RedditViewHolder holder, int position) {
        holder.bindRedditView(context, reddits.get(position));
    }

    @Override
    public int getItemCount() {
        return reddits.size();
    }

    public ArrayList<Reddit> getReddits() {
        return reddits;
    }
}
