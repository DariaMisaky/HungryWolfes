package com.example.hungrywolfes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfes.R
import com.example.hungrywolfes.network.ListMealCategory

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val data: MutableList<ListMealCategory> = mutableListOf()

    private var clickListener: (item: ListMealCategory) -> Unit = {}

    private var selectedItemPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return CategoryViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = data[position]
        holder.textview.text = item.strCategory

        if (selectedItemPosition == position) {
            holder.textview.setTextColor(
                ContextCompat.getColor(
                    holder.textview.context,
                    R.color.orange
                )
            )
            holder.line.visibility = View.VISIBLE
        } else {
            holder.textview.setTextColor(
                ContextCompat.getColor(
                    holder.textview.context,
                    R.color.black
                )
            )
            holder.line.visibility = View.INVISIBLE
        }

        // Assigns a [OnClickListener] to the button contained in the [ViewHolder]
        holder.textview.setOnClickListener {
            clickListener(data[position])


            val previousItem = selectedItemPosition
            selectedItemPosition = position
            notifyItemChanged(position)
            notifyItemChanged(previousItem)
        }
    }

    override fun getItemCount() = data.size

    fun setData(data: List<ListMealCategory>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun addNewItem(element: ListMealCategory) {
        data.add(element)
        notifyItemInserted(data.lastIndex)
    }

    fun setClickListener(listener: (item: ListMealCategory) -> Unit) {
        clickListener = listener
    }

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textview = view.findViewById<TextView>(R.id.button_item)
        val line = view.findViewById<View>(R.id.line)
    }
}
