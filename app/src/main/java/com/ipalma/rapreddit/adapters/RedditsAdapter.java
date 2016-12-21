package com.ipalma.rapreddit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ipalma.rapreddit.R;
import com.ipalma.rapreddit.viewholders.RedditViewHolder;

import java.util.List;

/**
 * Adapter for the reddits recycler view
 * Created by ivan on 21/12/2016.
 */

public class RedditsAdapter extends RecyclerView.Adapter<RedditViewHolder> {

    private final List<String> titles;

    public RedditsAdapter(List<String> titles) {
        this.titles = titles;
    }

    @Override
    public RedditViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_reddits, parent, false);

        return new RedditViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RedditViewHolder holder, int position) {
        holder.setTitle(titles.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }
}
