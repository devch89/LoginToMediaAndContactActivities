package com.example.mediaandcontactslogin.broadcasts

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.mediaandcontactslogin.R

class BatteryBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BATTERY_LOW) {
            // show battery notification when battery is low
            val notificationBuilder = NotificationCompat.Builder(context, "battery_channel")
                .setSmallIcon(R.drawable.baseline_battery_alert_24)
                .setContentTitle("Battery Low")
                .setContentText("Battery level is low, please charge your device.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
            notificationManager.notify(0,notificationBuilder.build())

        }
    }
}