package mibh.mis.tmsland.manager;

import android.content.Context;

public class PrefManage {

    private static PrefManage instance;

    public static PrefManage getInstance() {
        if (instance == null)
            instance = new PrefManage();
        return instance;
    }

    private Context mContext;

    private PrefManage() {
        mContext = Contextor.getInstance().getContext();
    }

    public static class Key {
        public static final String TMS = "info";
        public static final String truckId = "truckid";
        public static final String tailId = "tailId";
        public static final String driverId = "empid";
        public static final String firstName = "firstname";
        public static final String lastName = "lastname";
        public static final String tel = "tel";
        public static final String lastWork = "lastwork";
        public static final String latitude = "latitude";
        public static final String longitude = "longitude";
        public static final String locationname = "locationname";
        public static final String lastCheckStatus = "lastcheckstatus";
        public static final String statusTruck = "statusTruck";
        public static final String statusTail = "statusTail";
        public static final String statusDriver = "statusDriver";
        public static final String statusReserve = "statusReserve";
        public static final String firebaseToken = "firebaseToken";
        public static final String shortcutAdded = "shortcutAdded";
    }

    public String getTruckId() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getString(Key.truckId, "");
    }

    public String getTailId() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getString(Key.tailId, "");
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

    public String getLongitude() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getString(Key.longitude, "0");
    }

    public String getLocationName() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getString(Key.locationname, "Location not found");
    }

    public long getLastCheckStatus() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getLong(Key.lastCheckStatus, 0);
    }

    public String getStatusTruck() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getString(Key.statusTruck, "");
    }

    public String getStatusTail() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getString(Key.statusTail, "");
    }

    public String getStatusDriver() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getString(Key.statusDriver, "");
    }

    public String getStatusReserve() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getString(Key.statusReserve, "");
    }

    public String getFirebaseToken() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getString(Key.firebaseToken, "");
    }

    public boolean getShortcutAdded() {
        return mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).getBoolean(Key.shortcutAdded, false);
    }

    public void setStatusTruck(String statusTruck) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putString(Key.statusTruck, statusTruck).apply();
    }

    public void setStatusTail(String statusTail) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putString(Key.statusTail, statusTail).apply();
    }

    public void setStatusDriver(String statusDriver) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putString(Key.statusDriver, statusDriver).apply();
    }

    public void setStatusReserve(String statusReserve) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putString(Key.statusReserve, statusReserve).apply();
    }

    public void setTruckId(String truckId) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putString(Key.truckId, truckId).apply();
    }

    public void setTailId(String tailId) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putString(Key.tailId, tailId).apply();
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

    public void setLongitude(double longitude) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putString(Key.longitude, String.format("%.5f", longitude)).apply();
    }

    public void setLocationName(String locationName) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putString(Key.locationname, locationName).apply();
    }

    public void setLastCheckStatus(long currentDate) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putLong(Key.lastCheckStatus, currentDate).apply();
    }

    public void setFirebaseToken(String token) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putString(Key.firebaseToken, token).apply();
    }

    public void setShortcutAdded(boolean bool) {
        mContext.getSharedPreferences(Key.TMS, Context.MODE_PRIVATE).edit().putBoolean(Key.shortcutAdded, bool).apply();
    }

}
