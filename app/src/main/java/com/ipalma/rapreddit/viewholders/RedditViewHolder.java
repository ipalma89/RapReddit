package com.ipalma.rapreddit.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ipalma.rapreddit.R;

/**
 * View Holder for the reddits recycler view
 * Created by ivan on 21/12/2016.
 */

public class RedditViewHolder extends RecyclerView.ViewHolder {

    private TextView titleTextView;

    public RedditViewHolder(View itemView) {
        super(itemView);

        titleTextView = (TextView) itemView.findViewById(R.id.title);
    }

    public void setTitle(String title) {
        titleTextView.setText(title);
    }
}
