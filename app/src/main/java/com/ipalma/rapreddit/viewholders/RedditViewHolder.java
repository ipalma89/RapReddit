package com.ipalma.rapreddit.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ipalma.rapreddit.R;
import com.ipalma.rapreddit.models.Reddit;

/**
 * View Holder for the reddits recycler view
 * Created by ivan on 21/12/2016.
 */

public class RedditViewHolder extends RecyclerView.ViewHolder {

    private TextView titleTextView;
    private ImageView iconImageView;

    public RedditViewHolder(View itemView) {
        super(itemView);

        titleTextView = (TextView) itemView.findViewById(R.id.title);
        iconImageView = (ImageView) itemView.findViewById(R.id.icon_img);
    }

    public void bindRedditView(Context context, Reddit reddit) {
        titleTextView.setText(reddit.getTitle());
        Glide.with(context)
                .load(reddit.getIconImgUrl())
                .dontAnimate()
                .placeholder(R.drawable.placeholder)
                .into(iconImageView);
    }
}
