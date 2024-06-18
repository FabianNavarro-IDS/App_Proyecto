package com.example.elementosvisualestarea.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.elementosvisualestarea.CustomAdapter
import com.example.elementosvisualestarea.R

class FragmentList : Fragment() {
    private lateinit var lista: ListView
    private val so = arrayOf("Anakin Skywalker", "Luke Skywalker", "Leia Skywalker", "Mace Windu", "Obi Wan Kenobi", "Yoda", "Qui Gon Jinn")

    private val imgso = arrayOf(R.drawable.anakin, R.drawable.luke, R.drawable.leia, R.drawable.windu, R.drawable.obiwan, R.drawable.yoda,
        R.drawable.quigon)

    private val desc = arrayOf(
        "Anakin Skywalker, un hombre humano sensible a la Fuerza, fue un Caballero Jedi de la República Galáctica y el Elegido de la Fuerza.",
        "Luke Skywalker fue un legendario héroe de guerra y Jedi que fundó la Nueva Orden Jedi. Era el hijo del Caballero Jedi Anakin Skywalker y la senadora Padmé Amidala de Naboo, además hermano mellizo de Leia Organa.",
        "Leia Organa Solo (nacida Leia Amidala Skywalker) fue en diversas etapas de su vida política, revolucionaria y Caballero Jedi de la Nueva Orden Jedi. Hija del General Jedi Anakin Skywalker y la senadora Padmé Amidala de Naboo, Leia fue la hermana melliza de Luke Skywalker,",
        "Mace Windu era un korun que fue un prominente Maestro Jedi durante los últimos años de la República Galáctica. Proveniente del mundo de Haruun Kal, Windu sirvió como uno de los últimos miembros del Alto Consejo Jedi antes de la Gran Purga Jedi.",
        "Obi-Wan Kenobi fue un legendario Maestro Jedi que jugó un rol significante en el destino de la galaxia durante los últimos días de la República Galáctica. Fue el maestro de Anakin y Luke Skywalker, entrenándolos en los caminos de la Fuerza.",
        "Yoda era uno de los más renombrados y poderosos Maestros Jedi durante toda la historia de la galaxia, y uno de los pocos Jedis de la República Galáctica en sobrevivir hasta la Guerra Civil Galáctica.",
        "Qui-Gon Jinn fue un venerado, aunque disidente y poco convencional, Maestro Jedi humano. Fue Padawan del Conde Dooku, y mentor de Obi-Wan Kenobi y brevemente de Anakin Skywalker."
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