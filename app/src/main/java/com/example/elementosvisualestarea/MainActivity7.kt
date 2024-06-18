package com.example.elementosvisualestarea

import android.os.Bundle
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity7 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main9)

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
    }
}
