package mibh.mis.tmsland.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ponlakit on 3/27/2017.
 */

public class NewsDao implements Parcelable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("data")
    @Expose
    private SubNewsDao data;

    protected NewsDao(Parcel in) {
        type = in.readString();
        data = in.readParcelable(SubNewsDao.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeParcelable(data, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsDao> CREATOR = new Creator<NewsDao>() {
        @Override
        public NewsDao createFromParcel(Parcel in) {
            return new NewsDao(in);
        }

        @Override
        public NewsDao[] newArray(int size) {
            return new NewsDao[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SubNewsDao getData() {
        return data;
    }

    public void setData(SubNewsDao data) {
        this.data = data;
    }
}
