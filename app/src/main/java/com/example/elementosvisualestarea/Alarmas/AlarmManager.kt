package com.example.elementosvisualestarea.Alarmas

import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import com.example.elementosvisualestarea.MenuExpandible.DrawerBaseActivity
import com.example.elementosvisualestarea.R

class AlarmManager : DrawerBaseActivity() {

    private lateinit var timePicker: TimePicker
    private lateinit var btnSetAlarm: Button
    private lateinit var btnSendNotification: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_manager)

        // Inicializar vistas
        timePicker = findViewById(R.id.timePicker)
        btnSetAlarm = findViewById(R.id.btnSetAlarm)
        btnSendNotification = findViewById(R.id.btnSendNotification)

        // Configurar acciones de botones
        btnSetAlarm.setOnClickListener { setAlarm() }
        btnSendNotification.setOnClickListener { sendNotification() }
    }

    private fun setAlarm() {
        // Obtener hora seleccionada
        val hour = timePicker.hour
        val minute = timePicker.minute

        // Configurar alarma
        val alarmHelper = AlarmHelper(this)
        alarmHelper.setAlarm(hour, minute)

        Toast.makeText(this, "Alarma programada para las $hour:$minute", Toast.LENGTH_SHORT).show()
    }

    private fun sendNotification() {
        // Enviar notificación inmediata
        val notificationHelper = NotificationHelper(this)
        notificationHelper.showNotification(
            "Notificación Inmediata",
            "Esta es una notificación personalizada"
        )

        Toast.makeText(this, "Notificación enviada", Toast.LENGTH_SHORT).show()
    }
}