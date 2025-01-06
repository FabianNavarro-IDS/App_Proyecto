package com.example.elementosvisualestarea.Alarmas

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val notificationHelper = NotificationHelper(context)
        notificationHelper.showNotification(
            "Alarma Programada",
            "Es hora de tu evento programado"
        )
    }
}
