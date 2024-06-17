package com.example.elementosvisualestarea.fragment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.elementosvisualestarea.R

class ActivityDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val item = intent.getStringExtra("ITEM").toString()
        val imgitem = intent.getIntExtra("IMAGE", 0)
        val descitem = intent.getStringExtra("DESC").toString()

        val detail = supportFragmentManager.findFragmentById(R.id.activity_detail_fragment) as FragmentDetail?

        if(detail != null && detail.isInLayout()){
            detail.cargar_detalle(item)
            detail.cargar_imgdetalle(imgitem)
            detail.cargar_descdetalle(descitem)
        }
    }
}