package mibh.mis.tmsland.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.provider.Settings;
import android.renderscript.Double2;
import android.util.Log;

import mibh.mis.tmsland.manager.Contextor;

import static android.content.ContentValues.TAG;

public class Utils {

    private static Utils instance;

    public static Utils getInstance() {
        if (instance == null)
            instance = new Utils();
        return instance;
    }

    private Context mContext;

    private Utils() {
        mContext = Contextor.getInstance().getContext();
    }


    public String getDeviceId() {
        return Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public String getVersionName() {
        try {
            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            return pInfo.versionName;
        } catch (Exception e) {
            return "1.0";
        }
    }

    public String getInStationName(String sourceName, String destName, String sourceLatLong, String destLatLong, String currentLat, String currentLong) {
        String result;
        double diffFirst = getDistanceFormPoint(sourceLatLong, currentLat + "," + currentLong);
        double diffSecond = getDistanceFormPoint(destLatLong, currentLat + "," + currentLong);
        if (MyDebug.LOG)
            Log.d(TAG, "getInStationName: " + diffFirst + " " + diffSecond);
        if (diffFirst > diffSecond) {
            result = "ปลายทาง: " + destName;
        } else {
            result = "ต้นทาง: " + sourceName;
        }
        if ((diffFirst > 3000) && (diffSecond > 3000)) {
            result = "No";
        }
        return result;
    }

    public double getDistanceFormPoint(String firstLatLong, String secondLatLong) {
        String[] splFirst = firstLatLong.split(",");
        String[] splSecond = secondLatLong.split(",");

        double firstLat = Double.parseDouble(splFirst[0]);
        double firstLong = Double.parseDouble(splFirst[1]);
        double secondLat = Double.parseDouble(splSecond[0]);
        double secondLong = Double.parseDouble(splSecond[1]);
        double theta = secondLong - firstLong;
        double dist = Math.sin(deg2rad(firstLat)) * Math.sin(deg2rad(secondLat)) + Math.cos(deg2rad(firstLat)) * Math.cos(deg2rad(secondLat)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        dist = dist * 1000;

        return dist;
    }

    private double deg2rad(Double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(Double rad) {
        return (rad / Math.PI * 180.0);
    }

}
