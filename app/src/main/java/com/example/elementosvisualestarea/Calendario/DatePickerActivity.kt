package com.example.elementosvisualestarea.Calendario

import android.app.DatePickerDialog
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.elementosvisualestarea.MenuExpandible.DrawerBaseActivity
import com.example.elementosvisualestarea.R
import java.util.*

class DatePickerActivity : DrawerBaseActivity() {

    private lateinit var tvSelectedDate: TextView
    private lateinit var btnShowDatePicker: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_picker)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        btnShowDatePicker = findViewById(R.id.btnShowDatePicker)

        btnShowDatePicker.setOnClickListener {
            showDatePickerDialog()
        }

        // Configurar VideoView
        val helpVideo = findViewById<VideoView>(R.id.helpVideo)
        val videoUri = Uri.parse("android.resource://${packageName}/raw/video")
        helpVideo.setVideoURI(videoUri)
        helpVideo.setOnPreparedListener { it.isLooping = true } // Opcional: Loop infinito
        helpVideo.start()
    }

    private fun showDatePickerDialog() {
        // Obtener la fecha actual
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Crear el diálogo
        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            // Actualizar el TextView con la fecha seleccionada
            val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            tvSelectedDate.text = "Fecha seleccionada: $formattedDate"
        }, year, month, day)

        // Mostrar el diálogo
        datePickerDialog.show()
    }
}
