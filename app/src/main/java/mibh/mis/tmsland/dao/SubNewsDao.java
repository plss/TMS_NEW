package mibh.mis.tmsland.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ponlakit on 3/27/2017.
 */

public class SubNewsDao implements Parcelable {

    @SerializedName("img")
    @Expose
    private List<String> img = null;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;

    public SubNewsDao() {
    }

    protected SubNewsDao(Parcel in) {
        img = in.createStringArrayList();
        title = in.readString();
        content = in.readString();
    }

    public static final Creator<SubNewsDao> CREATOR = new Creator<SubNewsDao>() {
        @Override
        public SubNewsDao createFromParcel(Parcel in) {
            return new SubNewsDao(in);
        }

        @Override
        public SubNewsDao[] newArray(int size) {
            return new SubNewsDao[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(img);
        parcel.writeString(title);
        parcel.writeString(content);
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
