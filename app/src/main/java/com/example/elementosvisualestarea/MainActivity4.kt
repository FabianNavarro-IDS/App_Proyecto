package com.example.elementosvisualestarea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast


var r1: RadioButton? = null
var r2: RadioButton? = null
var r3: RadioButton? = null
var rG: RadioGroup? = null

class MainActivity4 : AppCompatActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    var jedi: CheckBox? = null
    var sith: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        rG?.setOnCheckedChangeListener(this)


    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId){
            r1?.id -> Toast.makeText(this, "Sable azul seleccionado", Toast.LENGTH_SHORT).show()
            r2?.id -> Toast.makeText(this, "Sable rojo seleccionado", Toast.LENGTH_SHORT).show()
            r3?.id -> Toast.makeText(this, "Sable verde seleccionado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(v: View?) {
        if (jedi?.isChecked == true){
            Toast.makeText(this, "Es un Jedi", Toast.LENGTH_SHORT).show()
        }

        if (sith?.isChecked == true){
            Toast.makeText(this, "Es un sith", Toast.LENGTH_SHORT).show()
        }
    }
}