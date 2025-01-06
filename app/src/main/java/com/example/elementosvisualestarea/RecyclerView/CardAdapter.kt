package com.example.elementosvisualestarea.RecyclerView

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.elementosvisualestarea.R

data class CardItem(val name: String, val imageResId: Int)

class CardAdapter(
    private val items: MutableList<CardItem>,
    private val onSelectionChanged: (Int) -> Unit
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    private val selectedItems = mutableSetOf<CardItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, selectedItems.contains(item))

        // Manejar selección
        holder.itemView.setOnClickListener {
            toggleSelection(item)
        }

        holder.itemView.setOnLongClickListener {
            toggleSelection(item)
            true
        }
    }

    override fun getItemCount(): Int = items.size

    private fun toggleSelection(item: CardItem) {
        if (selectedItems.contains(item)) {
            selectedItems.remove(item)
        } else {
            selectedItems.add(item)
        }
        notifyDataSetChanged()
        onSelectionChanged(selectedItems.size)
    }

    fun deleteSelectedItems() {
        items.removeAll(selectedItems)
        selectedItems.clear()
        notifyDataSetChanged()
    }

    fun editFirstSelectedItem() {
        if (selectedItems.isNotEmpty()) {
            val firstItem = selectedItems.first()
            val index = items.indexOf(firstItem)
            if (index != -1) {
                items[index] = firstItem.copy(name = "${firstItem.name} (Editado)")
                notifyItemChanged(index)
            }
        }
    }

    fun selectAll() {
        selectedItems.clear()
        selectedItems.addAll(items)
        notifyDataSetChanged()
        onSelectionChanged(selectedItems.size)
    }

    fun clearSelection() {
        selectedItems.clear()
        notifyDataSetChanged()
        onSelectionChanged(0)
    }

    fun getSelectedCount(): Int = selectedItems.size

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(item: CardItem, isSelected: Boolean) {
            textView.text = item.name
            imageView.setImageResource(item.imageResId)

            // Cambiar color del fondo según el estado de selección
            itemView.setBackgroundColor(if (isSelected) Color.LTGRAY else Color.WHITE)
        }
    }
}
