package com.example.elementosvisualestarea

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.PopupMenu
import android.widget.Toast
import com.example.elementosvisualestarea.Alarmas.AlarmManager
import com.example.elementosvisualestarea.MenuExpandible.DrawerBaseActivity

class MainActivity7 : DrawerBaseActivity() {

    private lateinit var popupmenu: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main9)

        popupmenu = findViewById(R.id.activity_main_popup)

        val checkBoxDarthVader = findViewById<CheckBox>(R.id.checkBoxDarthVader)
        val checkBoxDarthMaul = findViewById<CheckBox>(R.id.checkBoxDarthMaul)
        val checkBoxYoda = findViewById<CheckBox>(R.id.checkBoxYoda)
        val checkBoxObiWan = findViewById<CheckBox>(R.id.checkBoxObiWan)
        val checkBoxAnakin = findViewById<CheckBox>(R.id.checkBoxAnakin)
        val checkBoxAhsoka = findViewById<CheckBox>(R.id.checkBoxAhsoka)

        val checkBoxes = listOf(checkBoxDarthVader, checkBoxDarthMaul, checkBoxYoda, checkBoxObiWan, checkBoxAnakin, checkBoxAhsoka)

        checkBoxes.forEach { checkBox ->
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    Toast.makeText(this, "${checkBox.text} seleccionado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "${checkBox.text} deseleccionado", Toast.LENGTH_SHORT).show()
                }
            }
        }

        popupmenu.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val menu = PopupMenu(this@MainActivity7, view)
                val inflater: MenuInflater = menu.getMenuInflater()
                inflater.inflate(R.menu.activit_main_menu, menu.getMenu())
                menu.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener,
                    PopupMenu.OnMenuItemClickListener {
                    override fun onMenuItemClick(menuItem: MenuItem): Boolean {
                        return when (menuItem.itemId){
                            R.id.Sables -> mostrar_mensaje("Sables")
                            R.id.vader -> mostrar_mensaje("Darth Vader")
                            R.id.Jedis -> mostrar_mensaje("Jedis")
                            R.id.Acerca -> mostrar_mensaje("Acerca")
                            R.id.Naves -> mostrar_mensaje("Naves")
                            else -> false
                        }
                    }
                })
                menu.show()
            }
        })
    }

    fun mostrar_mensaje(msj: String) : Boolean {
        val toast = Toast.makeText(this, msj, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 0)
        toast.show()
        return true
    }
}
