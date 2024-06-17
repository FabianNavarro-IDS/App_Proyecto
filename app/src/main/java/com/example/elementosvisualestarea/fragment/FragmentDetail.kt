package com.example.elementosvisualestarea.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.elementosvisualestarea.R

class FragmentDetail : Fragment() {
    private lateinit var txtDetalle: TextView
    private lateinit var imgDetalle: ImageView
    private lateinit var descDetalle: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_detail, container, false)
        txtDetalle = view.findViewById(R.id.fragment_datail_os)
        imgDetalle = view.findViewById(R.id.fragment_detail_imgos)
        descDetalle = view.findViewById(R.id.fragment_datail_desc)
        return view
    }

    fun cargar_detalle(detalle: String){
        txtDetalle.text = detalle
    }

    fun cargar_imgdetalle(imgdetalle: Int){
        imgDetalle.setImageResource(imgdetalle)
    }

    fun cargar_descdetalle(descdetalle: String){
        descDetalle.text = descdetalle
    }
}