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
    private var alreadyExecuted=false
    private var selectedItemPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        if(!alreadyExecuted) {
            clickListener(data[0])
            alreadyExecuted=true
        }

        return CategoryViewHolder(layout)

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = data[position]
        holder.textview.text = item.strCategory
        Log.d("agg", "data= ${data[position]}")

        holder.textview.setOnClickListener {

            clickListener(item)
            val previousItem = selectedItemPosition
            selectedItemPosition = position
            notifyItemChanged(position)
            notifyItemChanged(previousItem)
        }

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
    }

    override fun getItemCount() = data.size

    fun setData(data: List<ListMealCategory>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }


    fun setClickListener(listener: (item: ListMealCategory) -> Unit) {
        clickListener = listener
    }

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textview = view.findViewById<TextView>(R.id.button_item)
        val line = view.findViewById<View>(R.id.line)
    }
}
