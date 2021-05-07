package com.example.remakeme;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;
import java.util.Random;
public class NotificationPublisher extends BroadcastReceiver {
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    public static String NOTIFICATION_ID = "notification-id";
    public static String NOTIFICATION = "notification";
    private final static String default_notification_channel_id = "default";
    private static final String EXTRA_MESSAGE = "MESSAGE_ID";
    private static String DATE_MESSAGE = "Meme";
    public static String getExtraMessage(){ return EXTRA_MESSAGE; }

    public void onReceive (Context context, Intent intent) {

        String message = intent.getStringExtra(EXTRA_MESSAGE);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = intent.getParcelableExtra(NOTIFICATION);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "NOTIFICATION_CHANNEL_NAME", importance);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }
        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;
        /*int id = intent.getIntExtra(NOTIFICATION_ID, 0 );*/
        assert notificationManager != null;
        notificationManager.notify(m, notification);
    }

    public static void scheduleEventNotification(Context context, Event event){
        assert(event.getSendReminders());//reminder for debugging
        Notification notification = getEventNotification(
                context,
                event.getLocation(),
                event.getEventName(),
                event.getRemindOffset(),
                event.getGroupColor(),
                event.getId(),
                event.getEventStart());

        Calendar now = Calendar.getInstance();
        long delay = event.getEventStart().getTimeInMillis() - now.getTimeInMillis() - event.getRemindOffset();
        scheduleNotification(context, notification, delay, ((Long)event.getId()).toString());
    }

    private static void scheduleNotification (Context context, Notification notification, long delay, String id) {
        Intent notificationIntent = new Intent(context, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, id);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        /*TextView textView = findViewById(R.id.editNumNotifies);*/
        PendingIntent pendingIntent = PendingIntent.getBroadcast (context, Integer.parseInt(id),
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private static Notification getEventNotification (Context context,
                                                      String location,
                                                      String eventName,
                                                      long reminderOffset,
                                                      int color,
                                                      long id,
                                                      Calendar start) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,
                default_notification_channel_id);
        builder.setContentTitle(eventName);
        builder.setContentText("Starting in " + reminderOffset / 60000 + " minutes at " + location + ".");
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setAutoCancel(true);
        builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        builder.setContentIntent(getEventNotificationIntent(context, eventName, id, start));
        if(color != 0) {
            builder.setColorized(true);
            builder.setColor(color);
        }
        return builder.build();
    }

    private static PendingIntent getEventNotificationIntent(Context context, String message, long id, Calendar start){
        Intent intent = new Intent(context, DayViewV2.class);
        Integer month = start.get(Calendar.MONTH);
        Integer day = start.get(Calendar.DAY_OF_MONTH);
        Integer year = start.get(Calendar.YEAR);
        String date = month.toString() + "/" + day.toString() + "/" + year.toString();
        intent.putExtra(DATE_MESSAGE, date);

        intent.putExtra(NotificationPublisher.getExtraMessage(), message);
        // Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(intent);
        return stackBuilder.getPendingIntent((int) id, 0);
    }
}
