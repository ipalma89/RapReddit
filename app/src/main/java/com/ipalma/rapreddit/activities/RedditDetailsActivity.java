package com.ipalma.rapreddit.activities;

import android.graphics.Point;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ipalma.rapreddit.R;
import com.ipalma.rapreddit.helpers.DataUtils;
import com.ipalma.rapreddit.models.Reddit;

import de.hdodenhof.circleimageview.CircleImageView;

public class RedditDetailsActivity extends AppCompatActivity {

    private Reddit reddit;
    private TextView titleTextView, subscribersTextView, descriptionTextView;
    private ImageView banner;
    private CircleImageView circleImage;
    private CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reddit_details);

        reddit = getIntent().getParcelableExtra("reddit");
        titleTextView = (TextView) findViewById(R.id.title);
        banner = (ImageView) findViewById(R.id.banner_image);
        circleImage = (CircleImageView) findViewById(R.id.circle_image);
        subscribersTextView = (TextView) findViewById(R.id.subscribers);
        subscribersTextView = (TextView) findViewById(R.id.subscribers);
        descriptionTextView = (TextView) findViewById(R.id.description);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        titleTextView.setText(reddit.getUrl());

        // set image view size to maintain aspect ratio
        int width = getWidth();
        int height = getHeight(width);
        ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        banner.setLayoutParams(layoutParams);

        // load banner
        Glide.with(this)
                .load(reddit.getBannerImgUrl())
                .fitCenter()
                .placeholder(R.drawable.banner_placeholder)
                .dontAnimate()
                .into(banner);

        // load circle icon image
        Glide.with(this)
                .load(reddit.getIconImgUrl())
                .dontAnimate()
                .placeholder(R.drawable.circle_placeholder)
                .into(circleImage);

        // set subscribers
        String subs = DataUtils.getFormattedNumber(reddit.getSubscribers());
        subscribersTextView.setText(getString(R.string.subscribers, subs));

        // set description
        descriptionTextView.setText(reddit.getDescription());

        collapsingToolbar.setTitle("ASD");
    }

    private int getWidth() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size.x;
    }

    private int getHeight(int width) {
        return width/(1280/384);
    }
}
