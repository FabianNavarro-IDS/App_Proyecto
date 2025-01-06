package com.example.elementosvisualestarea.MenuExpandible

import android.content.Intent
import android.view.MenuItem
import android.widget.ExpandableListView
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.elementosvisualestarea.Alarmas.AlarmManager
import com.example.elementosvisualestarea.Calendario.DatePickerActivity
import com.example.elementosvisualestarea.Camara.CameraActivity
import com.example.elementosvisualestarea.CrudActivity
import com.example.elementosvisualestarea.MainActivity6
import com.example.elementosvisualestarea.MainActivity7
import com.example.elementosvisualestarea.Mapa.MapsActivity
import com.example.elementosvisualestarea.R
import com.example.elementosvisualestarea.RecyclerView.RecyclerViewActivity
import com.example.elementosvisualestarea.Sensor.SensorActivity

abstract class DrawerBaseActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var expandableListView: ExpandableListView
    private lateinit var expandableListAdapter: ExpandableListAdapter

    private val groupList = listOf("Inicio")
    private val itemList = hashMapOf(
        "Inicio" to listOf("Sobre Mí", "Ayuda"),
    )

    override fun setContentView(layoutResID: Int) {
        val fullLayout = layoutInflater.inflate(R.layout.activity_drawer_base, null) as DrawerLayout
        val container = fullLayout.findViewById<FrameLayout>(R.id.container)
        layoutInflater.inflate(layoutResID, container, true)
        super.setContentView(fullLayout)

        drawerLayout = fullLayout
        expandableListView = findViewById(R.id.expandableListView)

        // Configurar el adaptador del ExpandableListView
        expandableListAdapter = ExpandableListAdapter(this, groupList, itemList)
        expandableListView.setAdapter(expandableListAdapter)

        // Configurar clics en las opciones estáticas
        findViewById<TextView>(R.id.nav_mapa).setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java)) // Navegar al mapa
        }

        findViewById<TextView>(R.id.nav_alarmas).setOnClickListener {
            startActivity(Intent(this, AlarmManager::class.java)) // Navegar a alarmas
        }

        findViewById<TextView>(R.id.nav_sensores).setOnClickListener {
            startActivity(Intent(this, SensorActivity::class.java)) // Navegar a sensores
        }

        findViewById<TextView>(R.id.nav_camara).setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java)) // Navegar a camara
        }

        findViewById<TextView>(R.id.nav_calendario).setOnClickListener {
            startActivity(Intent(this, DatePickerActivity::class.java)) // Navegar a calendario
        }

        findViewById<TextView>(R.id.nav_naves).setOnClickListener {
            startActivity(Intent(this, RecyclerViewActivity::class.java)) // Navegar a naves
        }

        findViewById<TextView>(R.id.nav_usuarios).setOnClickListener {
            startActivity(Intent(this, CrudActivity::class.java)) // Navegar a usuarios
        }

        // Configurar clics en las opciones dinámicas
        expandableListView.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            when (itemList[groupList[groupPosition]]?.get(childPosition)) {
                "Sobre Mí" -> startActivity(Intent(this, MainActivity7::class.java))
                "Ayuda" -> startActivity(Intent(this, MainActivity6::class.java))
            }
            false
        }

        // Configurar el botón de hamburguesa
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.drawer_open,
            R.string.drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (::drawerLayout.isInitialized && item.itemId == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
