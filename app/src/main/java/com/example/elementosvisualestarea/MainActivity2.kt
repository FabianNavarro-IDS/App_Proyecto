package com.example.elementosvisualestarea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {

    private lateinit var popupmenu: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        popupmenu = findViewById(R.id.btndarkside)

        popupmenu.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val menu = PopupMenu(this@MainActivity2, v)
                val inflater: MenuInflater = menu.menuInflater
                inflater.inflate(R.menu.activit_main_menu, menu.menu)

                menu.setOnMenuItemClickListener (object : MenuItem.OnMenuItemClickListener, PopupMenu.OnMenuItemClickListener {
                    override fun onMenuItemClick(item: MenuItem): Boolean {
                        return when (item.itemId){
                            R.id.Sables -> {
                                mostrar_mensaje("Sables de luz desde Menú Popup")
                            }
                            R.id.vader -> {
                                mostrar_mensaje("Darth Vader desde Menú Popup")
                            }
                            else -> false
                        }
                    }
                })
                menu.show()
            }
        })
    }

    fun menus (v: View) {
        when (v.getId()) {
            R.id.btnlightside -> {
                val intent = Intent(this, MainActivity3::class.java)
                startActivity(intent)
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