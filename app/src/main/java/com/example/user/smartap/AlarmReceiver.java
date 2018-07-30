package com.example.user.smartap;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.CompoundButton;
import android.widget.Toast;

import static com.example.user.smartap.OnOffActivity.mContext;
import static com.example.user.smartap.OnOffActivity.switch1;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationmanager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.on).setTicker("SMARTAP").setWhen(System.currentTimeMillis()).setNumber(1).setContentTitle("Alarm").setContentText("Smartap Alarm")
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setContentIntent(pendingIntent).setAutoCancel(true);

        notificationmanager.notify(1, builder.build());

        if (((OnOffActivity) OnOffActivity.mContext).switch1.isChecked()) {
            ((OnOffActivity) OnOffActivity.mContext).SwitchThread("2");
            ((OnOffActivity) OnOffActivity.mContext).switch1.setChecked(false);
            //((OnOffActivity)OnOffActivity.mContext).switch1.setChecked(false);
        } else {
            ((OnOffActivity) OnOffActivity.mContext).SwitchThread("1");

            ((OnOffActivity) mContext).switch1.setChecked(true);
        }
        //Intent mServiceintent=new Intent(context,AlarmService.class);
        //context.startService(mServiceintent);
    }
}
