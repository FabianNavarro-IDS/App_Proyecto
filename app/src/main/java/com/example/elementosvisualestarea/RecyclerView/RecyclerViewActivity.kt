package com.example.elementosvisualestarea.RecyclerView

import android.os.Bundle
import androidx.appcompat.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.elementosvisualestarea.MenuExpandible.DrawerBaseActivity
import com.example.elementosvisualestarea.R

class RecyclerViewActivity : DrawerBaseActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cardAdapter: CardAdapter
    private var actionMode: ActionMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        recyclerView = findViewById(R.id.recyclerView)

        // Lista de datos
        val cardList = mutableListOf( // Cambiado a mutableListOf
            CardItem("Halcon Milenario", R.drawable.halcon),
            CardItem("X-Wing", R.drawable.xwing),
            CardItem("Caza TIE", R.drawable.cazatie),
            CardItem("Destructor Estelar", R.drawable.destructor),
            CardItem("Lanzadera Imperial", R.drawable.lanzadera),
            CardItem("Razor Crest", R.drawable.razor),
            CardItem("Slave I", R.drawable.slave),
            CardItem("Caza N-1 Naboo", R.drawable.naboo),
            CardItem("Jedi Starfighter", R.drawable.jedistar)
        )

        // Configurar el Adapter y el LayoutManager
        cardAdapter = CardAdapter(cardList) { selectedCount ->
            if (selectedCount > 0) {
                if (actionMode == null) {
                    actionMode = startSupportActionMode(actionModeCallback)
                }
                actionMode?.title = "$selectedCount seleccionado(s)"
            } else {
                actionMode?.finish()
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = cardAdapter
    }

    // Callback para el ActionMode
    private val actionModeCallback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.contextual_menu, menu) // Inflar menú contextual
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            when (item?.itemId) {
                R.id.menu_delete -> {
                    cardAdapter.deleteSelectedItems()
                    mode?.finish()
                    return true
                }
                R.id.menu_edit -> {
                    cardAdapter.editFirstSelectedItem()
                    mode?.finish()
                    return true
                }
                R.id.menu_select_all -> {
                    cardAdapter.selectAll()
                    mode?.title = "${cardAdapter.getSelectedCount()} seleccionado(s)"
                    return true
                }
            }
            return false
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            actionMode = null
            cardAdapter.clearSelection() // Limpiar selección
        }
    }
}
