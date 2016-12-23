package com.ipalma.rapreddit.viewholders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ipalma.rapreddit.R;
import com.ipalma.rapreddit.activities.RedditDetailsActivity;
import com.ipalma.rapreddit.helpers.DataUtils;
import com.ipalma.rapreddit.models.Reddit;

/**
 * View Holder for the reddits recycler view
 * Created by ivan on 21/12/2016.
 */

public class RedditViewHolder extends RecyclerView.ViewHolder {

    private Reddit reddit;
    private ImageView iconImageView;
    private TextView urlTextView;
    private TextView publicDescTextView;
    private TextView subscribersTextView;

    public RedditViewHolder(final Context context, View itemView) {
        super(itemView);

        iconImageView = (ImageView) itemView.findViewById(R.id.icon_img);
        urlTextView = (TextView) itemView.findViewById(R.id.url);
        publicDescTextView = (TextView) itemView.findViewById(R.id.publicDescription);
        subscribersTextView = (TextView) itemView.findViewById(R.id.subscribers);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, RedditDetailsActivity.class);
                    intent.putExtra("reddit", reddit);
                    context.startActivity(intent);
                }
            }
        });
    }

    public void bindRedditView(Context context, Reddit reddit) {
        this.reddit = reddit;

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
