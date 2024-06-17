package com.example.elementosvisualestarea.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.elementosvisualestarea.R

class FragmentList : Fragment() {
    private lateinit var lista: ListView
    private val so = arrayOf("Anakin Skywalker", "Luke Skywalker", "Leia Skywalker", "Han Solo", "Obi Wan Kenobi", "Chewbacca", "Qui Gon Yin")

    private val imgso = arrayOf(R.drawable.vadericon, R.drawable.vadericon, R.drawable.vadericon, R.drawable.vadericon, R.drawable.vadericon, R.drawable.vadericon,
        R.drawable.vadericon)

    private val desc = arrayOf(
        "Un sistema operativo popular desarrollado por Microsoft.",
        "Un sistema operativo para las computadoras Macintosh de Apple.",
        "Una familia de sistemas operativos de código abierto similares a Unix.",
        "Un sistema operativo similar a Unix compuesto enteramente de software libre.",
        "Un sistema operativo móvil desarrollado por Google.",
        "Un sistema operativo Unix originalmente desarrollado por Sun Microsystems.",
        "Un sistema operativo empresarial de código abierto."
    )

    private val list: ArrayList<String> = ArrayList(so.toList())
    private val lists: ArrayList<Int> = ArrayList(imgso.toList())
    private val desclist: ArrayList<String> = ArrayList(desc.toList())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        lista = view.findViewById(R.id.main_activity_lista)

        val adapter = CustomAdapter(
            requireActivity(),
            list, lists
        )
        lista.setAdapter(adapter)
        lista.onItemClickListener = AdapterView.OnItemClickListener{ adapterView, view, i, l ->
            cargar_actividad(list[i], lists[i], desclist[i])
        }
        return view
    }

    fun cargar_actividad (item: String, imgitem: Int, descitem: String){

        val detail = requireActivity().supportFragmentManager.findFragmentById(R.id.activity_main_detail) as FragmentDetail?
        if (detail != null && detail.isInLayout){
            detail.cargar_imgdetalle(imgitem)
            detail.cargar_detalle(item)
            detail.cargar_descdetalle(descitem)
        } else {
            val intent = Intent(activity, ActivityDetail::class.java)
            intent.putExtra("ITEM", item)
            intent.putExtra("IMAGE", imgitem)
            intent.putExtra("DESC", descitem)
            startActivity(intent)
        }
    }
}