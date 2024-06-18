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
import androidx.appcompat.app.AlertDialog
import com.example.elementosvisualestarea.fragment.MainActivity

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val toggleButton = findViewById<ToggleButton>(R.id.TGButton)
        val mainLayout = findViewById<LinearLayout>(R.id.mainLayout)

        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            Log.d("MainActivity2", "ToggleButton isChecked: $isChecked")
            if (isChecked) {
                val intent = Intent(this, MainActivity4::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
            }
        }
    }

    fun sendanakin (v: View) {
        when (v.getId()) {
            R.id.anakin -> {
                val builder = AlertDialog.Builder(this@MainActivity2)
                val view = layoutInflater.inflate(R.layout.anakin, null)
                builder.setView(view)
                val dialog = builder.create()

                dialog.show()
            }
        }
    }

    fun sendobiwan (v: View) {
        when (v.getId()) {
            R.id.obiwan -> {
                val builder = AlertDialog.Builder(this@MainActivity2)
                val view = layoutInflater.inflate(R.layout.obiwan, null)
                builder.setView(view)
                val dialog = builder.create()

                dialog.show()
            }
        }
    }

    fun sendluke (v: View) {
        when (v.getId()) {
            R.id.luke -> {
                val builder = AlertDialog.Builder(this@MainActivity2)
                val view = layoutInflater.inflate(R.layout.luke, null)
                builder.setView(view)
                val dialog = builder.create()

                dialog.show()
            }
        }
    }

    fun sendashoka (v: View) {
        when (v.getId()) {
            R.id.ashoka -> {
                val builder = AlertDialog.Builder(this@MainActivity2)
                val view = layoutInflater.inflate(R.layout.ahsoka, null)
                builder.setView(view)
                val dialog = builder.create()

                dialog.show()
            }
        }
    }

    fun sendjedis (v: MenuItem): Boolean {
        when (v.itemId) {
            R.id.Jedis -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    fun sendacerca (v: MenuItem): Boolean {
        when (v.itemId) {
            R.id.Acerca -> {
                val intent = Intent(this, MainActivity3::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.activit_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.Sables -> mostrar_mensaje("Ayuda") && sendayuda(item)
            R.id.vader -> mostrar_mensaje("Sobre mi") && sendsobremi(item)
            R.id.Jedis -> sendjedis(item)
            R.id.Acerca -> sendacerca(item)
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun mostrar_mensaje(msj: String): Boolean {
        val toast = Toast.makeText(this, msj, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0 , 0)
        toast.show()

        return true
    }

    fun sendayuda (v: MenuItem): Boolean {
        when (v.itemId) {
            R.id.Sables -> {
                val intent = Intent(this, MainActivity6::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    fun sendsobremi (v: MenuItem): Boolean {
        when (v.itemId) {
            R.id.vader -> {
                val intent = Intent(this, MainActivity7::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}
