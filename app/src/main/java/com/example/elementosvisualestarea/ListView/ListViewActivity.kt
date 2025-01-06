package com.example.elementosvisualestarea.ListView

import android.os.Bundle
import android.view.ContextMenu
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.elementosvisualestarea.MenuExpandible.DrawerBaseActivity
import com.example.elementosvisualestarea.R

data class ListItem(val text: String, val imageResId: Int)

class ListViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val listView: ListView = findViewById(R.id.listView)

        // Lista de datos
        val items = listOf(
            ListItem("Halcon Milenario", R.drawable.halcon),
            ListItem("X-Wing", R.drawable.xwing),
            ListItem("Caza TIE", R.drawable.cazatie),
            ListItem("Destructor Estelar", R.drawable.destructor),
            ListItem("Lanzadera Imperial", R.drawable.lanzadera),
            ListItem("Razor Crest", R.drawable.razor),
            ListItem("Slave I", R.drawable.slave),
            ListItem("Caza N-1 Naboo", R.drawable.naboo),
            ListItem("Jedi Starfighter", R.drawable.jedistar)
        )

        // Configurar el adaptador
        val adapter = ListViewAdapter(this, items)
        listView.adapter = adapter

        // Acción al hacer clic en un elemento
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedItem = items[position]
            Toast.makeText(
                this,
                "Seleccionaste: ${selectedItem.text}",
                Toast.LENGTH_LONG
            ).show()
        }

        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.activit_main_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.Sables -> mostrar_mensaje("Sables")
            R.id.vader -> mostrar_mensaje("Darth Vader")
            R.id.Jedis -> mostrar_mensaje("Jedis")
            R.id.Acerca -> mostrar_mensaje("Acerca")
            R.id.Naves -> mostrar_mensaje("Naves")
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
