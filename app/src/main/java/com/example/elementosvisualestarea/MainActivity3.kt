package com.example.elementosvisualestarea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity3 : AppCompatActivity() {

    private lateinit var lista: ListView
    private val dias = arrayOf("Anakin Skywalker", "Luke Skywalker", "Leia Skywalker", "Han Solo", "Obi Wan Kenobi", "Chewbacca", "Qui Gon Yin")
    private val list: ArrayList<String> = ArrayList(dias.toList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        lista = findViewById(R.id.main_activity_lista)
        val adapter = ArrayAdapter(this, R.layout.activity_main3, R.id.main_activity_text, list)
        lista.setAdapter(adapter)

        lista.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, I ->
            Toast.makeText(
                this,
                list[i],
                Toast.LENGTH_SHORT
            ).show()
        }
        registerForContextMenu(lista)
    }

    fun mostrar_mensaje(msj: String): Boolean {
        val toast = Toast.makeText(this, msj, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
        toast.show()

        return true
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.activit_main_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.Sables -> {
                mostrar_mensaje ("Sables de Luz desde Menú de Opciones Contextual")
            }
            R.id.vader -> {
                mostrar_mensaje ("Darth Vader desde Menú de Opciones Contextual")
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.activit_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.Sables -> mostrar_mensaje("Has Seleccionado sables de luz desde menu de opciones")
            R.id.vader -> mostrar_mensaje("Has seleccionado a Darth Vader desde menu de opciones")
            else -> super.onOptionsItemSelected(item)
        }
    }
}