package com.example.hungrywolfes

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlin.coroutines.coroutineContext

class CategoryAdapter:RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    private val list = ('A').rangeTo('Z').toList()
    class CategoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_item)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return CategoryViewHolder(layout)
    }


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = list.get(position)
        holder.button.text = item.toString()

        // Assigns a [OnClickListener] to the button contained in the [ViewHolder]
        holder.button.setOnClickListener {
           // holder.button.setTextColor(Color.YELLOW)
            holder.view.findNavController().navigate(R.id.action_homeScreen_to_fragment_search_screen)

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}