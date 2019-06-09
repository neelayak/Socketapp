package net.iquesoft.socketapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Videohelper implements Parcelable {
    String description, id, thumb, title, url;

    Videohelper() {

    }

    protected Videohelper(Parcel in) {
        description = in.readString();
        id = in.readString();
        thumb = in.readString();
        title = in.readString();
        url = in.readString();
    }

    public static final Creator<Videohelper> CREATOR = new Creator<Videohelper>() {
        @Override
        public Videohelper createFromParcel(Parcel in) {
            return new Videohelper(in);
        }

        @Override
        public Videohelper[] newArray(int size) {
            return new Videohelper[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(id);
        dest.writeString(thumb);
        dest.writeString(title);
        dest.writeString(url);
    }
}
