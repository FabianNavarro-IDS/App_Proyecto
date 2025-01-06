package com.example.elementosvisualestarea

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import android.widget.VideoView
import com.example.elementosvisualestarea.MenuExpandible.DrawerBaseActivity

class MainActivity6 : DrawerBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)

        // Configurar Spinner
        val problemSpinner = findViewById<Spinner>(R.id.problemSpinner)
        val problems = arrayOf("Navegación", "Interfaz", "Inicio de sesión", "Apariencia")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, problems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        problemSpinner.adapter = adapter

        problemSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@MainActivity6, "Seleccionado: $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No hacer nada
            }
        }

        // Configurar VideoView
        val helpVideo = findViewById<VideoView>(R.id.helpVideo)
        val videoUri = Uri.parse("android.resource://${packageName}/raw/help_video") // Reemplaza con tu video
        helpVideo.setVideoURI(videoUri)
        helpVideo.setOnPreparedListener { it.isLooping = true } // Opcional: Loop infinito
        helpVideo.start()
    }
}
