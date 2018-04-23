package com.aad.core.gaad.enhanced_system_integration.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.aad.core.gaad.MainActivity;
import com.aad.core.gaad.R;

public class NotificationUtility {

    public static final int REPLY_INTENT_ID = 0;
    public static final int ARCHIVE_INTENT_ID = 1;

    public static final int REMOTE_INPUT_ID = 1247;

    public static final String LABEL_REPLY = "Reply";
    public static final String LABEL_ARCHIVE = "Archive";
    public static final String REPLY_ACTION = "com.hitherejoe.notifi.util.ACTION_MESSAGE_REPLY";
    public static final String KEY_PRESSED_ACTION = "KEY_PRESSED_ACTION";
    public static final String KEY_TEXT_REPLY = "KEY_TEXT_REPLY";
    private static final String KEY_NOTIFICATION_GROUP = "KEY_NOTIFICATION_GROUP";

    public void showStandardNotification(Context context) {
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Standard Notification")
                .setContentText("This is just a standard notification!")
                .setLargeIcon(largeIcon)
                .setColor(ContextCompat.getColor(context, R.color.colorAccent))
                .setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_HIGH);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, builder.build());
    }


    public void showStandardHeadsUpNotification(Context context) {

        PendingIntent archiveIntent = PendingIntent.getActivity(context,
                ARCHIVE_INTENT_ID,
                getMessageReplyIntent(LABEL_ARCHIVE), PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Action replyAction =
                new NotificationCompat.Action.Builder(android.R.drawable.sym_def_app_icon,
                        LABEL_REPLY, archiveIntent)
                        .build();
        NotificationCompat.Action archiveAction =
                new NotificationCompat.Action.Builder(android.R.drawable.sym_def_app_icon,
                        LABEL_ARCHIVE, archiveIntent)
                        .build();

        NotificationCompat.Builder notificationBuider = createNotificationBuider(
                context, "Heads up!", "This is a normal heads up notification");
        notificationBuider.setPriority(Notification.PRIORITY_HIGH).setVibrate(new long[0]);
        notificationBuider.addAction(replyAction);
        notificationBuider.addAction(archiveAction);

        Intent push = new Intent();
        push.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        push.setClass(context, MainActivity.class);

        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(context, 0,
                push, PendingIntent.FLAG_CANCEL_CURRENT);
        notificationBuider.setFullScreenIntent(fullScreenPendingIntent, true);
        showNotification(context, notificationBuider.build(), 0);
    }

    private Intent getMessageReplyIntent(String label) {
        return new Intent()
                .addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
                .setAction(REPLY_ACTION)
                .putExtra(KEY_PRESSED_ACTION, label);
    }

    private NotificationCompat.Builder createNotificationBuider(Context context, String title, String message) {
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.ic_launcher);
        return new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setLargeIcon(largeIcon)
                .setColor(ContextCompat.getColor(context, R.color.colorAccent))
                .setAutoCancel(true);
    }

    private void showNotification(Context context, Notification notification, int id) {
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(id, notification);
    }
}
