package com.ipalma.rapreddit.activities;

import android.graphics.Point;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Display;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ipalma.rapreddit.R;
import com.ipalma.rapreddit.helpers.DataUtils;
import com.ipalma.rapreddit.models.Reddit;

import de.hdodenhof.circleimageview.CircleImageView;

public class RedditDetailsActivity extends AppCompatActivity {

    private Reddit reddit;
    private TextView titleTextView, subscribersTextView;
    private ImageView banner;
    private CircleImageView circleImage;
    private CollapsingToolbarLayout collapsingToolbar;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reddit_details);

        reddit = getIntent().getParcelableExtra("reddit");
        setToolbar();
        initializeViews();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initializeViews() {
        titleTextView = (TextView) findViewById(R.id.title);
        banner = (ImageView) findViewById(R.id.banner_image);
        circleImage = (CircleImageView) findViewById(R.id.circle_image);
        subscribersTextView = (TextView) findViewById(R.id.subscribers);
        subscribersTextView = (TextView) findViewById(R.id.subscribers);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        webView = (WebView) findViewById(R.id.webview);

        // set header title
        titleTextView.setText(reddit.getUrl());

        // set image view size to maintain aspect ratio
        setBannerImageViewSize();

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
        webView.loadData(Html.fromHtml(reddit.getDescription()).toString(),"text/html","utf-8");

        collapsingToolbar.setTitle(reddit.getDisplayName());
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));
    }

    private void setBannerImageViewSize() {
        int width = getWidth();
        int height = getHeight(width);
        ViewGroup.LayoutParams layoutParams = banner.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        banner.setLayoutParams(layoutParams);
    }

    private int getWidth() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size.x;
    }

    private int getHeight(int imageViewWidth) {
        int width = reddit.getBannerImgSize()[0];
        int height = reddit.getBannerImgSize()[1];

        return (int)(imageViewWidth/((float)width/(float)height));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
