package mibh.mis.tmsland.dao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ponlakit on 4/8/2017.
 */

public class MtnCloseParams implements Parcelable {

    private String truckId;
    private String tailId;
    private String statusTruck;
    private String statusTruckTh;
    private String statusTail;
    private String statusTailTh;
    private String statusDriver;
    private String statusDriverTh;
    private long lastCheck;
    private int count;

    public MtnCloseParams() {
    }

    protected MtnCloseParams(Parcel in) {
        truckId = in.readString();
        tailId = in.readString();
        statusTruck = in.readString();
        statusTruckTh = in.readString();
        statusTail = in.readString();
        statusTailTh = in.readString();
        statusDriver = in.readString();
        statusDriverTh = in.readString();
        lastCheck = in.readLong();
        count = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(truckId);
        dest.writeString(tailId);
        dest.writeString(statusTruck);
        dest.writeString(statusTruckTh);
        dest.writeString(statusTail);
        dest.writeString(statusTailTh);
        dest.writeString(statusDriver);
        dest.writeString(statusDriverTh);
        dest.writeLong(lastCheck);
        dest.writeInt(count);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MtnCloseParams> CREATOR = new Creator<MtnCloseParams>() {
        @Override
        public MtnCloseParams createFromParcel(Parcel in) {
            return new MtnCloseParams(in);
        }

        @Override
        public MtnCloseParams[] newArray(int size) {
            return new MtnCloseParams[size];
        }
    };

    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public String getTailId() {
        return tailId;
    }

    public void setTailId(String tailId) {
        this.tailId = tailId;
    }

    public String getStatusTruck() {
        return statusTruck;
    }

    public void setStatusTruck(String statusTruck) {
        this.statusTruck = statusTruck;
    }

    public String getStatusTruckTh() {
        return statusTruckTh;
    }

    public void setStatusTruckTh(String statusTruckTh) {
        this.statusTruckTh = statusTruckTh;
    }

    public String getStatusTail() {
        return statusTail;
    }

    public void setStatusTail(String statusTail) {
        this.statusTail = statusTail;
    }

    public String getStatusTailTh() {
        return statusTailTh;
    }

    public void setStatusTailTh(String statusTailTh) {
        this.statusTailTh = statusTailTh;
    }

    public String getStatusDriver() {
        return statusDriver;
    }

    public void setStatusDriver(String statusDriver) {
        this.statusDriver = statusDriver;
    }

    public String getStatusDriverTh() {
        return statusDriverTh;
    }

    public void setStatusDriverTh(String statusDriverTh) {
        this.statusDriverTh = statusDriverTh;
    }

    public long getLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(long lastCheck) {
        this.lastCheck = lastCheck;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
