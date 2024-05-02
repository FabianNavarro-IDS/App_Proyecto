package com.example.elementosvisualestarea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val nombres = arrayOf("Anakin", "ObiWan", "Ashoka", "Luke")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actv = findViewById<AutoCompleteTextView>(R.id.actUsuario)
        val adapter = ArrayAdapter(applicationContext,android.R.layout.simple_spinner_dropdown_item, nombres)

        actv.setAdapter(adapter)
        actv.setOnClickListener(OnItemClickListener { parent, view, position, id ->
            Toast.makeText(applicationContext, "Nombre seleccionado = " + nombres[position],Toast.LENGTH_SHORT).show()
        })
    }

    fun send  (v: View) {
        val usu = findViewById<AutoCompleteTextView>(R.id.actUsuario)
        val con = findViewById<EditText>(R.id.contra)
        val id = findViewById<EditText>(R.id.id)

        var bndusu = true
        var bndcon = true
        var bndid = true

        usu.error = null
        con.error = null
        id.error = null

        if (usu.text.toString().trim().isEmpty()){
            bndusu = false
            usu.error = "Porfavor esciba su usuario"
        }

        if (con.text.toString().trim().isEmpty()){
            bndcon = false
            con.error = "Porfavor escriba una contraseña"
        }

        if (id.text.toString().trim().isEmpty()){
            bndid = false
            id.error = "Porfavor ingrese un Id"
        }

        if ( bndusu && bndcon && bndid) {
            when (v.getId()) {
                R.id.sesion -> {
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}

private fun AutoCompleteTextView.setOnClickListener(onItemClickListener: AdapterView.OnItemClickListener) {

}
