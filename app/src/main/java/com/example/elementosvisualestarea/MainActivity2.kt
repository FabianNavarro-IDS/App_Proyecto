package com.example.elementosvisualestarea

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.ToggleButton

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val toggleButton = findViewById<ToggleButton>(R.id.TGButton)
        val mainLayout = findViewById<LinearLayout>(R.id.mainLayout)

        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            Log.d("MainActivity2", "ToggleButton isChecked: $isChecked")
            if (isChecked) {
                mainLayout.setBackgroundColor(Color.RED)
            } else {
                mainLayout.setBackgroundColor(Color.BLUE)
            }
        }
    }

    fun sendanakin (v: View) {
        when (v.getId()) {
            R.id.anakin -> {
                val intent = Intent(this, MainActivity4::class.java)
                startActivity(intent)
            }
        }
    }

    fun sendobiwan (v: View) {
        when (v.getId()) {
            R.id.obiwan -> {
                val intent = Intent(this, MainActivity4::class.java)
                startActivity(intent)
            }
        }
    }

    fun sendluke (v: View) {
        when (v.getId()) {
            R.id.luke -> {
                val intent = Intent(this, MainActivity4::class.java)
                startActivity(intent)
            }
        }
    }

    fun sendashoka (v: View) {
        when (v.getId()) {
            R.id.ashoka -> {
                val intent = Intent(this, MainActivity4::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.activit_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.Sables -> mostrar_mensaje("Sables de luz desde menu de opciones")
            R.id.vader -> mostrar_mensaje("Darth Vader desde menu de opciones")
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun mostrar_mensaje(msj: String): Boolean {
        val toast = Toast.makeText(this, msj, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0 , 0)
        toast.show()

        return true
    }
}
