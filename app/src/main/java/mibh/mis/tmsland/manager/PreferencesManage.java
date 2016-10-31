package mibh.mis.tmsland.manager;

import android.content.Context;

import mibh.mis.tmsland.activity.LocationBaseActivity;

public class PreferencesManage {

    private static PreferencesManage instance;

    public static PreferencesManage getInstance() {
        if (instance == null)
            instance = new PreferencesManage();
        return instance;
    }

    private Context mContext;

    private PreferencesManage() {
        mContext = Contextor.getInstance().getContext();
    }

    public static class Key {
        public static final String TMS = "info";
        public static final String truckId = "truckid";
        public static final String driverId = "empid";
        public static final String firstName = "firstname";
        public static final String lastName = "lastname";
        public static final String tel = "tel";
        public static final String lastWork = "lastwork";
        public static final String latitude = "latitude";
        public static final String longtitude = "longtitude";
        public static final String locationname = "locationname";
        public static final String lastCheckStatus = "lastcheckstatus";
    }

    public String getTruckId() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getString(Key.truckId, "");
    }

    public String getDriverId() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getString(Key.driverId, "");
    }

    public String getFirstName() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getString(Key.firstName, "");
    }

    public String getLastName() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getString(Key.lastName, "");
    }

    public String getTel() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getString(Key.tel, "");
    }

    public String getLastWork() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getString(Key.lastWork, "");
    }

    public String getLatitude() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getString(Key.latitude, "0");
    }

    public String getLongtitude() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getString(Key.longtitude, "0");
    }

    public String getLocationName() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getString(Key.locationname, "Location not found");
    }

    public long getLastCheckStatus() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getLong(Key.lastCheckStatus, 0);
    }

    public void setTruckId(String truckId) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putString(Key.truckId, truckId).apply();
    }

    public void setDriverId(String driverId) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putString(Key.driverId, driverId).apply();
    }

    public void setFirstName(String firstName) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putString(Key.firstName, firstName).apply();
    }

    public void setLastName(String lastName) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putString(Key.lastName, lastName).apply();
    }

    public void setTel(String tel) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putString(Key.tel, tel).apply();
    }

    public void setLastWork(String lastWork) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putString(Key.lastWork, lastWork).apply();
    }

    public void setLatitude(double latitude) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putString(Key.latitude, String.format("%.5f", latitude)).apply();
    }

    public void setLongtitude(double longtitude) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putString(Key.longtitude, String.format("%.5f", longtitude)).apply();
    }

    public void setLocationName(String locationName) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putString(Key.locationname, locationName).apply();
    }

    public void setLastCheckStatus(long currentDate) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putLong(Key.lastCheckStatus, currentDate).apply();
    }

}
