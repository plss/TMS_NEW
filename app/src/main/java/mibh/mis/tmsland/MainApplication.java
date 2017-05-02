package mibh.mis.tmsland;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import mibh.mis.tmsland.activity.SplashScreen;
import mibh.mis.tmsland.manager.Contextor;
import mibh.mis.tmsland.manager.PrefManage;
import mibh.mis.tmsland.service.UploadImageService;
import mibh.mis.tmsland.utils.MyDebug;

/**
 * Created by ponlakiss on 05/11/2016.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
        FirebaseApp.initializeApp(getApplicationContext());
        FirebaseMessaging.getInstance().subscribeToTopic("tmsNews");
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        /*For delete old table*/
        //RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);
        createShortcutIcon();
        startService();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    private void createShortcutIcon() {
        boolean shortCutWasAlreadyAdded = PrefManage.getInstance().getShortcutAdded();
        if (shortCutWasAlreadyAdded) return;

        Intent shortcutIntent = new Intent(getApplicationContext(), SplashScreen.class);
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "TMS");
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.mipmap.ic_launcher));
        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(addIntent);

        PrefManage.getInstance().setShortcutAdded(true);
    }

    public void startService() {
        Log.i("SERVICE", "Service created...");
        Intent startServiceIntent = new Intent(this, UploadImageService.class);
        PendingIntent startWebServicePendingIntent = PendingIntent.getService(this, 0, startServiceIntent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 60 * 1000 * 30, startWebServicePendingIntent);
    }

}
