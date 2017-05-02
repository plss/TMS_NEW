package mibh.mis.tmsland.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.provider.Settings;
import android.renderscript.Double2;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mibh.mis.tmsland.activity.MainActivity;
import mibh.mis.tmsland.manager.Contextor;

import static android.content.ContentValues.TAG;
import static android.os.Parcelable.CONTENTS_FILE_DESCRIPTOR;

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
            result = "ระหว่างทาง: ";
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

    public void showAlertDialog(String message) {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(Contextor.getInstance().getContext());
        builderSingle.setMessage(message);
        builderSingle.setPositiveButton("ตกลง",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builderSingle.show();
    }

    public void showSweetAlertDialog(Context context, String title, String content) {
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .show();
    }

    public void showAlertErrorFinish(final Activity activity, String title, String content) {
        new SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        activity.finish();
                    }
                })
                .show();
    }

    public String getCurrentDateTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = date.format(c.getTime());
        return formattedDate;
    }

    public Date getDateFormString(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public boolean isDiffMoreThanMonths(Date startDate, Date endDate) {
        long difference = Math.abs(endDate.getTime() - startDate.getTime());
        long differenceDates = difference / (24 * 60 * 60 * 1000);
        String dayDifference = Long.toString(differenceDates);
        return differenceDates >= 30;
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public boolean isGpsEnable() {
        LocationManager manager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return false;
        }
        return true;
    }

}
