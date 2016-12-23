package com.ipalma.rapreddit.models;

import com.google.gson.annotations.SerializedName;

public class Reddit {

    private String id;
    @SerializedName("display_name") private String displayName;
    private String title;
    private String url;
    @SerializedName("icon_img") private String iconImgUrl;
    @SerializedName("banner_img") private String bannerImgUrl;
    @SerializedName("header_img") private String headerImgUrl;
    @SerializedName("public_description_html") private String publicDescHtml;
    @SerializedName("public_description") private String publicDesc;
    private long subscribers;
    private long created;

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getIconImgUrl() {
        return iconImgUrl;
    }

    public String getBannerImgUrl() {
        return bannerImgUrl;
    }

    public String getHeaderImgUrl() {
        return headerImgUrl;
    }

    public String getPublicDescHtml() {
        return publicDescHtml;
    }

    public String getPublicDesc() {
        return publicDesc;
    }

    public long getSubscribers() {
        return subscribers;
    }

    public long getCreated() {
        return created;
    }
}
