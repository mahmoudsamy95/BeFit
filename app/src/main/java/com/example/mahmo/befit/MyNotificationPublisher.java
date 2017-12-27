package com.example.mahmo.befit;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import java.util.Date;


public class MyNotificationPublisher extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int notifiID = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent repeating_intent = new Intent(context, RepeatingActivity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
if(intent.getStringExtra("id").equals("1")){


        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, repeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("pill reminder")
                .setContentText("You should take your pills now!")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.befit)
                .setLargeIcon(((BitmapDrawable) context.getResources().getDrawable(R.drawable.befit)).getBitmap())
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        builder.setContentIntent(pendingIntent);
        notificationManager.notify(345, builder.build());}

else if(intent.getStringExtra("id").equals("2")){
    PendingIntent pendingIntent = PendingIntent.getActivity(context, 2, repeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);
    NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
            .setContentTitle("checkup reminder")
            .setContentText("You have a checkup today!")
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.befit)
            .setLargeIcon(((BitmapDrawable) context.getResources().getDrawable(R.drawable.befit)).getBitmap())
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
    builder.setContentIntent(pendingIntent);
    notificationManager.notify(654, builder.build());}
else{
    PendingIntent pendingIntent = PendingIntent.getActivity(context, 2, repeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);
    NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
            .setContentTitle("Water reminder")
            .setContentText("You should drink cup of water now!")
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.befit)
            .setLargeIcon(((BitmapDrawable) context.getResources().getDrawable(R.drawable.befit)).getBitmap())
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
    builder.setContentIntent(pendingIntent);
    notificationManager.notify(1000, builder.build());
}


    }
}
