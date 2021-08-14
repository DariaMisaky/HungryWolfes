package com.example.hungrywolfes.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolfes.R
import com.example.hungrywolfes.network.ListMealCategory
import com.example.hungrywolfes.ui.overview.OverviewViewModel

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val data: MutableList<ListMealCategory> = mutableListOf()
    private var clickListener: (item: ListMealCategory) -> Unit = {}
    private var alreadyExecuted = false
    private var selectedItemPosition = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        if (!alreadyExecuted) {
            clickListener(data[0])
            alreadyExecuted = true
        }

        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return CategoryViewHolder(layout)

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = data[position]
        holder.textview.text = item.strCategory



        holder.textview.setOnClickListener {
            clickListener(item)
            val previousItem = selectedItemPosition
            selectedItemPosition = position
            notifyItemChanged(position)
            notifyItemChanged(previousItem)
        }

        updateButtonDesign(holder, position)

    }

    override fun getItemCount() = data.size

    fun setData(data: List<ListMealCategory>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()

    }

    private fun updateButtonDesign(holder: CategoryViewHolder, position: Int) {
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
                    R.color.textCategory
                )
            )
            holder.line.visibility = View.INVISIBLE
        }

    }


    fun setClickListener(listener: (item: ListMealCategory) -> Unit) {
        clickListener = listener
    }

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textview = view.findViewById<TextView>(R.id.button_item)
        val line = view.findViewById<View>(R.id.line)
    }
}
