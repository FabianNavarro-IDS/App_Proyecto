package com.example.elementosvisualestarea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    val nombres = arrayOf("Anakin", "ObiWan", "Ashoka", "Luke")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actv = findViewById<AutoCompleteTextView>(R.id.actUsuario)
        val adapter = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1, nombres)

        actv.setAdapter(adapter)
        actv.setOnClickListener(OnItemClickListener { parent, view, position, id ->
            Toast.makeText(applicationContext, "Nombre seleccionado = " + nombres[position],Toast.LENGTH_SHORT).show()
        })
    }

    fun send  (v: View) {
        val usu = findViewById<AutoCompleteTextView>(R.id.actUsuario)
        val con = findViewById<EditText>(R.id.contra)

        val Lbt = findViewById<RadioButton>(R.id.RbuttonLuz)
        val Obt = findViewById<RadioButton>(R.id.RbuttonOsc)

        val builder = AlertDialog.Builder(this@MainActivity)
        val view = layoutInflater.inflate(R.layout.dialog, null)

        builder.setView(view)
        val dialog = builder.create()
        view.findViewById<Button>(R.id.Okbutton).setOnClickListener{
            dialog.hide()
        }

        var bndusu = true
        var bndcon = true

        usu.error = null
        con.error = null

        if (usu.text.toString().trim().isEmpty()){
            dialog.show()
            bndusu = false
            usu.error = "Porfavor esciba su usuario"
        }

        if (con.text.toString().trim().isEmpty()){
            dialog.show()
            bndcon = false
            con.error = "Porfavor escriba una contraseña"
        }

        if ( bndusu && bndcon) {
            if (Lbt.isChecked) {
                when (v.getId()) {
                    R.id.sesion -> {
                        val intent = Intent(this, MainActivity2::class.java)
                        startActivity(intent)
                    }
                }
            } else if (Obt.isChecked) {
                when (v.getId()) {
                    R.id.sesion -> {
                        val intent = Intent(this, MainActivity4::class.java)
                        startActivity(intent)
                    }
                }
            }

        }
    }
}

private fun AutoCompleteTextView.setOnClickListener(onItemClickListener: AdapterView.OnItemClickListener) {

}
