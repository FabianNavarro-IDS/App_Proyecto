package com.example.elementosvisualestarea.Fragments.fragmentsith

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import com.example.elementosvisualestarea.Fragments.CustomAdapter
import com.example.elementosvisualestarea.R

class FragmentList : Fragment() {
    private lateinit var lista: ListView
    private val so = arrayOf("Darth Vader", "Darth Sidious", "Darth Maul", "Darth Revan", "Conde Dooku", "Kylo Ren", "Darth Malak")

    private val imgso = arrayOf(R.drawable.vader, R.drawable.sidious, R.drawable.maul, R.drawable.revan, R.drawable.dooku, R.drawable.kylo,
        R.drawable.malak)

    private val desc = arrayOf(
        "Darth Vader, un Señor Oscuro de los Sith y aprendiz del Emperador Darth Sidious. Como Lord Sith, Vader se volvió contra sus antiguos camaradas y dió caza a los Jedi sobrevivientes hasta su casi extinción.",
        "Sheev Palpatine, también conocido como Darth Sidious, fue un humano sensible a la Fuerza que sirvió como el último Canciller de la República Galáctica y el primer Emperador del Imperio Galáctico. Fue un Señor Oscuro de los Sith que seguía la Regla de Dos, un antiguo principio de la Orden de los Lores Sith, y fue el Lord Sith más poderoso en la historia galáctica.",
        "Darth Maul fue un zabrak dathomiriano y Lord Sith que vivió durante los últimos años de la República Galáctica.",
        "Revan—renombrado como el Revanchista, honorado como el Revan, denostado como Revan el Carnicero, temido como el Señor Oscuro de los Sith Darth Revan, y alabado como el Caballero Pródigo",
        "Dooku fue un Maestro Jedi que cayó al lado oscuro de la Fuerza, convirtiéndose entonces en un Lord Oscuro de los Sith.",
        "Ben Solo fue un hombre humano sensible a la Fuerza que, después de caer al lado oscuro de la Fuerza, eventualmente renunció a su alter ego Kylo Ren y fue redimido. Siendo maestro de los Caballeros de Ren, conquistó gran parte de la galaxia durante su reinado como Líder Supremo de la Primera Orden. ",
        "Darth Malak fue un humano que reinó como Señor Oscuro de los Sith durante la Guerra Civil Jedi. Antes de convertirse en un Lord Sith"
    )

    private val list: ArrayList<String> = ArrayList(so.toList())
    private val lists: ArrayList<Int> = ArrayList(imgso.toList())
    private val desclist: ArrayList<String> = ArrayList(desc.toList())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list2, container, false)
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