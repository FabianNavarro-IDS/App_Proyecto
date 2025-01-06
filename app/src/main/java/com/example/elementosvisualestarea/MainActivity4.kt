package com.example.elementosvisualestarea

import android.content.Intent
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
import com.example.elementosvisualestarea.Fragments.fragmentsith.MainActivity
import com.example.elementosvisualestarea.ListView.ListViewActivity

class MainActivity4 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val toggleButton = findViewById<ToggleButton>(R.id.TGButton)
        val mainLayout = findViewById<LinearLayout>(R.id.mainLayout)

        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            Log.d("MainActivity2", "ToggleButton isChecked: $isChecked")
            if (isChecked) {
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
            }
        }
    }

    fun sendanakin (v: View) {
        when (v.getId()) {
            R.id.anakin -> {
                val builder = AlertDialog.Builder(this@MainActivity4)
                val view = layoutInflater.inflate(R.layout.vader, null)
                builder.setView(view)
                val dialog = builder.create()

                dialog.show()
            }
        }
    }

    fun sendobiwan (v: View) {
        when (v.getId()) {
            R.id.obiwan -> {
                val builder = AlertDialog.Builder(this@MainActivity4)
                val view = layoutInflater.inflate(R.layout.maul, null)
                builder.setView(view)
                val dialog = builder.create()

                dialog.show()
            }
        }
    }

    fun sendluke (v: View) {
        when (v.getId()) {
            R.id.luke -> {
                val builder = AlertDialog.Builder(this@MainActivity4)
                val view = layoutInflater.inflate(R.layout.sidious, null)
                builder.setView(view)
                val dialog = builder.create()

                dialog.show()
            }
        }
    }

    fun sendashoka (v: View) {
        when (v.getId()) {
            R.id.ashoka -> {
                val builder = AlertDialog.Builder(this@MainActivity4)
                val view = layoutInflater.inflate(R.layout.dooku, null)
                builder.setView(view)
                val dialog = builder.create()

                dialog.show()
            }
        }
    }

    fun sendsith (v: MenuItem): Boolean {
        when (v.itemId) {
            R.id.Siths -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    fun sendacercasith (v: MenuItem): Boolean {
        when (v.itemId) {
            R.id.AcercaSith -> {
                val intent = Intent(this, MainActivity5::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    fun sendnaves (v: MenuItem): Boolean {
        when (v.itemId) {
            R.id.Naves -> {
                val intent = Intent(this, ListViewActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.activity_menu_manusiths, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.Sables -> mostrar_mensaje("Ayuda") && sendayuda(item)
            R.id.vader -> mostrar_mensaje("Sobre mi") && sendsobremi(item)
            R.id.Siths -> sendsith(item)
            R.id.AcercaSith -> sendacercasith(item)
            R.id.Naves -> sendnaves(item)
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