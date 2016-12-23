package com.ipalma.rapreddit.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ipalma.rapreddit.R;
import com.ipalma.rapreddit.helpers.DataUtils;
import com.ipalma.rapreddit.models.Reddit;

/**
 * View Holder for the reddits recycler view
 * Created by ivan on 21/12/2016.
 */

public class RedditViewHolder extends RecyclerView.ViewHolder {

    private ImageView iconImageView;
    private TextView urlTextView;
    private TextView publicDescTextView;
    private TextView subscribersTextView;

    public RedditViewHolder(View itemView) {
        super(itemView);

        iconImageView = (ImageView) itemView.findViewById(R.id.icon_img);
        urlTextView = (TextView) itemView.findViewById(R.id.url);
        publicDescTextView = (TextView) itemView.findViewById(R.id.publicDescription);
        subscribersTextView = (TextView) itemView.findViewById(R.id.subscribers);
    }

    public void bindRedditView(Context context, Reddit reddit) {
        // Set top text view (url + date)
        String date = DataUtils.getFormattedDate(reddit.getCreated());
        urlTextView.setText(context.getString(R.string.row_url_time, reddit.getUrl(), date));

        // Set description
        publicDescTextView.setText(reddit.getPublicDesc());

        // Set bottom text view (subscribers)
        String subs = DataUtils.getFormattedNumber(reddit.getSubscribers());
        subscribersTextView.setText(context.getString(R.string.subscribers, subs));

        // Use image library to load image
        Glide.with(context)
                .load(reddit.getIconImgUrl())
                .dontAnimate()
                .placeholder(R.drawable.placeholder)
                .into(iconImageView);
    }
}
