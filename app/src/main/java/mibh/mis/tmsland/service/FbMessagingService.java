package mibh.mis.tmsland.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import me.leolin.shortcutbadger.ShortcutBadger;
import mibh.mis.tmsland.R;
import mibh.mis.tmsland.activity.DialogNotificationActivity;
import mibh.mis.tmsland.activity.MainActivity;
import mibh.mis.tmsland.activity.SplashScreen;
import mibh.mis.tmsland.manager.Contextor;

import static android.support.v4.app.NotificationCompat.PRIORITY_HIGH;
import static android.support.v4.app.NotificationCompat.PRIORITY_MAX;
import static android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC;


/**
 * Created by Ponlakit on 3/16/2017.
 */

public class FbMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        String title = data.get("title");
        String body = data.get("body");
        String type = data.get("type");
        if (type != null && type.equalsIgnoreCase("new_plan")) {
            String planCount = data.get("plan_count");
            int badgeCount = Integer.parseInt(planCount);
            ShortcutBadger.applyCount(Contextor.getInstance().getContext(), badgeCount);
        }
        sendNotification(title, body);
    }

    private void sendNotification(String txt, String box) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_NO_CREATE);
        Notification notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(txt)
                        .setContentText(box)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .setVisibility(VISIBILITY_PUBLIC)
                        .setPriority(PRIORITY_MAX)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1000, notification);

        /*Intent dialogIntent = new Intent(this, DialogNotificationActivity.class);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);*/
    }
}
