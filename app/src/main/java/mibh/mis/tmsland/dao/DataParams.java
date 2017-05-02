package mibh.mis.tmsland.dao;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

/**
 * Created by Ponlakit on 7/25/2016.
 */

public class DataParams implements Parcelable {

    private String type;
    private String docId;
    private String itemId;
    private String typeImg;
    private String mode;
    private String detail;
    private String status;


    public DataParams() {

    }

    protected DataParams(Parcel in) {
        type = in.readString();
        docId = in.readString();
        itemId = in.readString();
        typeImg = in.readString();
        mode = in.readString();
        detail = in.readString();
        status = in.readString();
    }

    public static final Creator<DataParams> CREATOR = new Creator<DataParams>() {
        @Override
        public DataParams createFromParcel(Parcel in) {
            return new DataParams(in);
        }

        @Override
        public DataParams[] newArray(int size) {
            return new DataParams[size];
        }
    };

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDocId() {
        return docId == null ? "" : docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getItemId() {
        return itemId == null ? "" : itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTypeImg() {
        return typeImg == null ? "" : typeImg;
    }

    public void setTypeImg(String typeImg) {
        this.typeImg = typeImg;
    }

    public String getMode() {
        return mode == null ? "" : mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getDetail() {
        return detail == null ? "" : detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status == null ? "" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeString(docId);
        parcel.writeString(itemId);
        parcel.writeString(typeImg);
        parcel.writeString(mode);
        parcel.writeString(detail);
        parcel.writeString(status);
    }
}
