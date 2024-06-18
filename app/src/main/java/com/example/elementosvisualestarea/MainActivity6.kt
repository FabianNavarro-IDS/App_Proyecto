package com.example.elementosvisualestarea

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity6 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)

        // Obtener referencia al Spinner
        val problemSpinner = findViewById<Spinner>(R.id.problemSpinner)

        // Crear un ArrayAdapter usando un arreglo de strings y un layout de spinner predeterminado
        val problems = arrayOf("Navegación", "Interfaz", "Inicio de sesión", "Apariencia")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, problems)

        // Especificar el layout que se usará cuando aparezcan las opciones de la lista
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Aplicar el adaptador al Spinner
        problemSpinner.adapter = adapter

        // Configurar un listener para manejar la selección de elementos
        problemSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(this@MainActivity6, "Seleccionado: $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No hacer nada
            }
        }
    }
}
