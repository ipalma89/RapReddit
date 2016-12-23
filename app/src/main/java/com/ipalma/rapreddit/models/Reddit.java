package com.ipalma.rapreddit.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Reddit implements Parcelable {

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(displayName);
        dest.writeString(title);
        dest.writeString(url);
        dest.writeString(iconImgUrl);
        dest.writeString(bannerImgUrl);
        dest.writeString(headerImgUrl);
        dest.writeString(publicDescHtml);
        dest.writeString(publicDesc);
        dest.writeLong(subscribers);
        dest.writeLong(created);
    }

    private Reddit(Parcel parcel) {
        id = parcel.readString();
        displayName = parcel.readString();
        title = parcel.readString();
        url = parcel.readString();
        iconImgUrl = parcel.readString();
        bannerImgUrl = parcel.readString();
        headerImgUrl = parcel.readString();
        publicDescHtml = parcel.readString();
        publicDesc = parcel.readString();
        subscribers = parcel.readLong();
        created = parcel.readLong();
    }

    public static final Parcelable.Creator<Reddit> CREATOR = new Parcelable.Creator<Reddit>() {

        @Override
        public Reddit createFromParcel(Parcel source) {
            return new Reddit(source);
        }

        @Override
        public Reddit[] newArray(int size) {
            return new Reddit[size];
        }
    };
}
