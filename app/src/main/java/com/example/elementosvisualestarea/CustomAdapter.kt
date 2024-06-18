package com.example.elementosvisualestarea

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.elementosvisualestarea.R

class CustomAdapter (context: Context, private val items: List<String>, private val images: List<Int>) :
    ArrayAdapter<String>(context, R.layout.fragment_list, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.fragment_list, parent, false)

        val itemText: TextView = view.findViewById(R.id.main_activity_texto)
        val itemImage: ImageView = view.findViewById(R.id.main_activity_imagen)

        itemText.text = items[position]
        itemImage.setImageResource(images[position])

        return view
    }
}
